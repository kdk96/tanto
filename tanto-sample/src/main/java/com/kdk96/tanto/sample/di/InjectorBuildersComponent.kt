package com.kdk96.tanto.sample.di

import com.kdk96.tanto.InjectorBuildersProvider
import dagger.Component

@Component(
    modules = [
        AppComponentBuilderModule::class,
        AppActivityBuilderModule::class,
        SimpleComponentBuilderModule::class
    ]
)
interface InjectorBuildersComponent {
    fun builders(): InjectorBuildersProvider
}
