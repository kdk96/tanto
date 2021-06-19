package io.github.kdk96.tanto.sample.di

import dagger.Component
import io.github.kdk96.tanto.core.InjectorBuildersProvider

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
