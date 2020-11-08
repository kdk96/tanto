package com.kdk96.tanto.sample.di

import com.kdk96.tanto.ComponentDependencies
import com.kdk96.tanto.Injector
import com.kdk96.tanto.android.findComponentDependencies
import com.kdk96.tanto.injectorBuilder
import com.kdk96.tanto.sample.data.Storage
import com.kdk96.tanto.sample.ui.SimpleFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
object SimpleComponentBuilderModule {
    @Provides
    @IntoMap
    @ClassKey(SimpleFragment::class)
    fun provideBuilder() = injectorBuilder<SimpleFragment> {
        DaggerSimpleComponent.builder()
            .simpleDependencies(findComponentDependencies())
            .build()
    }
}

@Component(
    dependencies = [
        SimpleDependencies::class
    ]
)
interface SimpleComponent : Injector<SimpleFragment>

interface SimpleDependencies : ComponentDependencies {
    fun storage(): Storage
}
