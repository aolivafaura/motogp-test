package com.oliva.antonio.brastlewarkguide.di

import com.oliva.antonio.brastlewarkguide.ui.main.detail.DetailFragment
import com.oliva.antonio.brastlewarkguide.ui.main.list.GnomesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by antonio on 1/19/18.
 */

@Module
abstract class MainActivityFragmentsBuilder {

    @ContributesAndroidInjector(modules = [
        (GnomesListFragment.GnomesListFragmentModule::class),
        (GnomesListFragment.ListFragmentProvider::class)])
    abstract fun contributeListFragment(): GnomesListFragment

    @ContributesAndroidInjector(modules = [
        (DetailFragment.DetailFragmentModule::class),
        (DetailFragment.DetailFragmentProvider::class)])
    abstract fun contributeDetailFragment(): DetailFragment
}