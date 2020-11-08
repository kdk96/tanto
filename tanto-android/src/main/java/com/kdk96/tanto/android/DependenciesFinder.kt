package com.kdk96.tanto.android

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

private fun <T : ComponentDependencies> Fragment.findDependenciesFromParentRecursively(clazz: Class<T>): T? {
    var depsProviderFragment = parentFragment
    while (depsProviderFragment != null) {
        val deps = depsProviderFragment.viewModels<InjectorHolderViewModel<*>>()
            .value
            .injector
            ?.takeIf(clazz::isInstance)
            ?.let(clazz::cast)
        if (deps != null) {
            return deps
        }
        depsProviderFragment = depsProviderFragment.parentFragment
    }
    return null
}

private fun <T : ComponentDependencies> ComponentActivity.findDependencies(clazz: Class<T>): T? =
    viewModels<InjectorHolderViewModel<*>>()
        .value
        .injector
        ?.takeIf(clazz::isInstance)
        ?.let(clazz::cast)
