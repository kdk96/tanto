package com.kdk96.tanto.android

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.kdk96.tanto.Injector
import com.kdk96.tanto.InjectorBuilder
import com.kdk96.tanto.Tanto

@Suppress("UNCHECKED_CAST")
fun <T : Fragment> T.inject() {
    val injectorHolder = viewModels<InjectorHolderViewModel<T>>().value
    if (injectorHolder.injector == null) {
        val injectorBuilder = Tanto.builders[javaClass] as? InjectorBuilder<T>
            ?: throw IllegalStateException("Can't find suitable ${InjectorBuilder::class.java.simpleName} for $this")
        injectorHolder.injector = injectorBuilder.build(this)
    }
    injectorHolder.injector!!.inject(this)
}

@Suppress("UNCHECKED_CAST")
fun <T : ComponentActivity> T.inject() {
    val injectorHolder = viewModels<InjectorHolderViewModel<T>>().value
    if (injectorHolder.injector == null) {
        val injectorBuilder = Tanto.builders[javaClass] as? InjectorBuilder<T>
            ?: throw IllegalStateException("Can't find suitable ${InjectorBuilder::class.java.simpleName} for $this")
        injectorHolder.injector = injectorBuilder.build(this)
    }
    injectorHolder.injector!!.inject(this)
}

internal class InjectorHolderViewModel<T : Any> : ViewModel() {

    internal var injector: Injector<T>? = null

    override fun onCleared() {
        injector = null
    }
}
