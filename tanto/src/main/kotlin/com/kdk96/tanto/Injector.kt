package com.kdk96.tanto

interface Injector<T : Any> {
    fun inject(target: T)
}
