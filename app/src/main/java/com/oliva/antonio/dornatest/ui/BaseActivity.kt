package com.oliva.antonio.dornatest.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.oliva.antonio.dornatest.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by antonio
 */

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector, FragmentManager.OnBackStackChangedListener {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        super.onCreate(savedInstanceState)
    }

    protected fun replaceFragment(newFragment: BaseFragment, fragmentTag: String, backStack: Boolean) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        transaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right)
        transaction.replace(R.id.container, newFragment, fragmentTag)
        if (backStack) {
            transaction.addToBackStack(fragmentTag)
        }
        transaction.commitAllowingStateLoss()
    }

    protected fun setFragment(fragment: BaseFragment) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment)
                .commitAllowingStateLoss()
    }

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)

        //Handle when activity is recreated like on orientation Change
        shouldDisplayHomeUp()
    }

    override fun onBackStackChanged() {
        shouldDisplayHomeUp()
    }

    private fun shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        val canGoBack = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(canGoBack)
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }
}