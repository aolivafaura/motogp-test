package com.oliva.antonio.dornatest.di

import com.oliva.antonio.dornatest.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by antonio
 */
@Module
abstract class ActivitiesBuilderModule {
    @ContributesAndroidInjector(modules = [(MainActivity.MainActivityModule::class),
        (MainActivityFragmentsBuilder::class)])
    abstract fun contributesMainActivity(): MainActivity
}
