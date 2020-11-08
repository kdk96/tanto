package com.kdk96.tanto.sample.di

import android.content.Context
import androidx.preference.PreferenceManager
import com.kdk96.tanto.ComponentDependencies
import com.kdk96.tanto.Injector
import com.kdk96.tanto.injectorBuilder
import com.kdk96.tanto.sample.App
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
object AppComponentBuilderModule {
    @Provides
    @IntoMap
    @ClassKey(App::class)
    fun provideBuilder() = injectorBuilder<App> {
        DaggerAppComponent.factory()
            .create(this)
    }
}

@Singleton
@Component(
    modules = [
        AppModule::class,
        BindsModule::class
    ]
)
interface AppComponent : Injector<App>, AppActivityDependencies {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

@Module
object AppModule {
    @Provides
    @Singleton
    fun providePreferences(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)
}

@Module
interface BindsModule {
    @Binds
    fun bindsComponentDependencies(appComponent: AppComponent): ComponentDependencies
}
