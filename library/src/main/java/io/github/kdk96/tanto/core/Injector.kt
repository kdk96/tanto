package io.github.kdk96.tanto.core

public interface Injector<T : Any> {
    public fun inject(target: T)
}
