package com.kdk96.tanto

public interface Injector<T : Any> {
    public fun inject(target: T)
}
