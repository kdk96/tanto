package com.kdk96.tanto

interface ComponentDependencies

interface DependenciesOwner {
    val dependencies: ComponentDependencies
}
