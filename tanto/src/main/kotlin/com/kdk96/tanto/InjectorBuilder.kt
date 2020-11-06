package com.kdk96.tanto

fun interface InjectorBuilder<T : Any> {
    fun build(target: T): Injector<T>
}
