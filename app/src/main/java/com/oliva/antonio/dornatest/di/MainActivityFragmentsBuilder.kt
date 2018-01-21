package com.oliva.antonio.dornatest.di

import com.oliva.antonio.dornatest.ui.main.detail.EventDetailFragment
import com.oliva.antonio.dornatest.ui.main.list.EventListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by antonio on 1/19/18.
 */

@Module
abstract class MainActivityFragmentsBuilder {

    @ContributesAndroidInjector(modules = [
        (EventListFragment.EventListFragmentModule::class),
        (EventListFragment.ListFragmentProvider::class)])
    abstract fun contributeListFragment(): EventListFragment

    @ContributesAndroidInjector(modules = [
        (EventDetailFragment.EventDetailFragmentModule::class),
        (EventDetailFragment.DetailFragmentProvider::class)])
    abstract fun contributeDetailFragment(): EventDetailFragment
}