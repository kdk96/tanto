package com.kdk96.tanto.android

import android.content.Context
import androidx.fragment.app.Fragment
import com.kdk96.tanto.ComponentDependencies
import com.kdk96.tanto.HasChildDependencies

inline fun <reified T : ComponentDependencies> Context.findComponentDependencies(): T =
    findComponentDependencies(T::class.java)

fun <T : ComponentDependencies> Context.findComponentDependencies(clazz: Class<T>): T =
    applicationContext?.findDependencies(clazz)
        ?: throw IllegalStateException("Can't find suitable ${HasChildDependencies::class.java.simpleName} for $this")

inline fun <reified T : ComponentDependencies> Fragment.findComponentDependencies(): T =
    findComponentDependencies(T::class.java)

fun <T : ComponentDependencies> Fragment.findComponentDependencies(clazz: Class<T>): T =
    findDependenciesFromParentRecursively(clazz)
        ?: activity?.findDependencies(clazz)
        ?: activity?.application?.findDependencies(clazz)
        ?: throw IllegalStateException("Can't find suitable ${HasChildDependencies::class.java.simpleName} for $this")

private fun <T : ComponentDependencies> Fragment.findDependenciesFromParentRecursively(clazz: Class<T>): T? {
    var depsProviderFragment = parentFragment
    while (depsProviderFragment != null) {
        val deps = depsProviderFragment.findDependencies(clazz)
        if (deps != null) {
            return deps
        }
        depsProviderFragment = depsProviderFragment.parentFragment
    }
    return null
}

private fun <T : ComponentDependencies> Any.findDependencies(clazz: Class<T>): T? {
    if (this !is HasChildDependencies) return null
    return dependencies[clazz]
        ?.takeIf(clazz::isInstance)
        ?.let(clazz::cast)
}
