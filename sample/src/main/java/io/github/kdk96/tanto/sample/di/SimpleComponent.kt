package io.github.kdk96.tanto.sample.di

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import io.github.kdk96.tanto.android.findComponentDependencies
import io.github.kdk96.tanto.core.ComponentDependencies
import io.github.kdk96.tanto.core.Injector
import io.github.kdk96.tanto.core.injectorBuilder
import io.github.kdk96.tanto.sample.data.Storage
import io.github.kdk96.tanto.sample.ui.SimpleFragment

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
