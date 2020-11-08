package com.kdk96.tanto.sample.di

import android.content.SharedPreferences
import com.kdk96.tanto.ComponentDependencies
import com.kdk96.tanto.Injector
import com.kdk96.tanto.android.findComponentDependencies
import com.kdk96.tanto.injectorBuilder
import com.kdk96.tanto.sample.ui.AppActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
object AppActivityBuilderModule {
    @Provides
    @IntoMap
    @ClassKey(AppActivity::class)
    fun provideBuilder() = injectorBuilder<AppActivity> {
        DaggerAppActivityComponent.builder()
            .appActivityDependencies(findComponentDependencies())
            .build()
    }
}

@Singleton
@Component(
    dependencies = [
        AppActivityDependencies::class
    ]
)
interface AppActivityComponent : Injector<AppActivity>, SimpleDependencies

interface AppActivityDependencies : ComponentDependencies {
    fun preferences(): SharedPreferences
}
