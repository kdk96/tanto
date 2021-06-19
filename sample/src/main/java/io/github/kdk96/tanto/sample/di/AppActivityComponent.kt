package io.github.kdk96.tanto.sample.di

import android.content.SharedPreferences
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import io.github.kdk96.tanto.android.findComponentDependencies
import io.github.kdk96.tanto.core.ComponentDependencies
import io.github.kdk96.tanto.core.Injector
import io.github.kdk96.tanto.core.injectorBuilder
import io.github.kdk96.tanto.sample.ui.AppActivity
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
