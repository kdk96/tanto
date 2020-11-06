package com.kdk96.tanto

import dagger.MapKey
import kotlin.reflect.KClass

interface ComponentDependencies

typealias ComponentDependenciesProvider = Map<Class<out ComponentDependencies>, @JvmSuppressWildcards ComponentDependencies>

interface HasChildDependencies {
    val dependencies: ComponentDependenciesProvider
}

@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class ComponentDependenciesKey(val value: KClass<out ComponentDependencies>)
