package com.kdk96.tanto

fun interface InjectorBuilder<T : Any> {
    fun build(target: T): Injector<T>
}

inline fun <T : Any> injectorBuilder(
    crossinline builder: T.() -> Injector<T>
): InjectorBuilder<*> = InjectorBuilder<T> { target ->
    builder(target)
}
