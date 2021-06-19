package io.github.kdk96.tanto.core

public fun interface InjectorBuilder<T : Any> {
    public fun build(target: T): Injector<T>
}

public inline fun <T : Any> injectorBuilder(
    crossinline builder: T.() -> Injector<T>
): InjectorBuilder<*> = InjectorBuilder<T> { target ->
    builder(target)
}
