package com.oliva.antonio.dornatest.di

import com.oliva.antonio.dornatest.DornaApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by antonio on 12/3/17.
 */

@Singleton
@Component(modules = [
    (AndroidSupportInjectionModule::class),
    (AppModule::class),
    (RepositoryModule::class),
    (NetworkModule::class),
    (ActivitiesBuilderModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: DornaApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: DornaApp)
}