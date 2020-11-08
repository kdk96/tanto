package com.kdk96.tanto.sample

import android.app.Application
import com.kdk96.tanto.ComponentDependencies
import com.kdk96.tanto.DependenciesOwner
import com.kdk96.tanto.Tanto
import com.kdk96.tanto.inject
import com.kdk96.tanto.sample.di.DaggerInjectorBuildersComponent
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
