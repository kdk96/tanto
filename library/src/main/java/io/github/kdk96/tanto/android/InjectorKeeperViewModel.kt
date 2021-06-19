package io.github.kdk96.tanto.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import io.github.kdk96.tanto.core.Injector
import io.github.kdk96.tanto.core.InjectorBuilder
import io.github.kdk96.tanto.core.Tanto

@Suppress("UNCHECKED_CAST")
public fun <T : ViewModelStoreOwner> T.inject() {
    val injectorKeeper = ViewModelProvider(this).get<InjectorKeeperViewModel<T>>()
    if (injectorKeeper.injector == null) {
        val injectorBuilder = Tanto.builders[javaClass] as? InjectorBuilder<T>
            ?: throw IllegalStateException("Can't find suitable ${InjectorBuilder::class.java.simpleName} for $this")
        injectorKeeper.injector = injectorBuilder.build(this)
    }
    injectorKeeper.injector!!.inject(this)
}

internal inline fun <reified T : ViewModel> ViewModelProvider.get(): T = get(T::class.java)

internal class InjectorKeeperViewModel<T : Any> : ViewModel() {

    internal var injector: Injector<T>? = null

    override fun onCleared() {
        injector = null
    }
}
