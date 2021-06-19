package io.github.kdk96.tanto.sample

import android.app.Application
import io.github.kdk96.tanto.core.ComponentDependencies
import io.github.kdk96.tanto.core.DependenciesOwner
import io.github.kdk96.tanto.core.Tanto
import io.github.kdk96.tanto.core.inject
import io.github.kdk96.tanto.sample.di.DaggerInjectorBuildersComponent
import javax.inject.Inject

class App : Application(), DependenciesOwner {

    @Inject
    override lateinit var dependencies: ComponentDependencies

    override fun onCreate() {
        super.onCreate()
        Tanto.initBuilders(DaggerInjectorBuildersComponent.create().builders())
        inject()
    }
}
