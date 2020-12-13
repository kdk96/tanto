package com.kdk96.tanto.android

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.kdk96.tanto.ComponentDependencies
import com.kdk96.tanto.DependenciesOwner

inline fun <reified T : ComponentDependencies> Context.findComponentDependencies(): T =
    findComponentDependencies(T::class.java)

fun <T : ComponentDependencies> Context.findComponentDependencies(clazz: Class<T>): T =
    (applicationContext as? DependenciesOwner)?.dependencies
        ?.takeIf(clazz::isInstance)
        ?.let(clazz::cast)
        ?: throw IllegalStateException("Can't find suitable ${DependenciesOwner::class.java.simpleName} for $this")

inline fun <reified T : ComponentDependencies> Fragment.findComponentDependencies(): T =
    findComponentDependencies(T::class.java)

fun <T : ComponentDependencies> Fragment.findComponentDependencies(clazz: Class<T>): T =
    findDependenciesFromParentRecursively(clazz)
        ?: activity?.findDependencies(clazz)
        ?: activity?.findComponentDependencies(clazz)
        ?: throw IllegalStateException("Can't find suitable ${DependenciesOwner::class.java.simpleName} for $this")

private tailrec fun <T : ComponentDependencies> Fragment.findDependenciesFromParentRecursively(clazz: Class<T>): T? {
    val dependenciesProviderFragment = parentFragment ?: return null
    return dependenciesProviderFragment.findDependencies(clazz)
        ?: dependenciesProviderFragment.findDependenciesFromParentRecursively(clazz)
}

private fun <T : ComponentDependencies> ViewModelStoreOwner.findDependencies(clazz: Class<T>): T? =
    ViewModelProvider(this).get<InjectorKeeperViewModel<*>>()
        .injector
        ?.takeIf(clazz::isInstance)
        ?.let(clazz::cast)
