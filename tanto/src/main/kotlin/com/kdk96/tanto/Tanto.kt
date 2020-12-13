package com.kdk96.tanto

import java.util.Collections

typealias InjectorBuildersProvider = Map<Class<*>, InjectorBuilder<*>>

object Tanto {

    @Volatile
    lateinit var builders: InjectorBuildersProvider
        private set

    fun initBuilders(builders: InjectorBuildersProvider) {
        if (this::builders.isInitialized) {
            throw IllegalStateException("builders have already been initialized")
        } else {
            synchronized(this) {
                if (this::builders.isInitialized) {
                    throw IllegalStateException("builders have already been initialized")
                } else {
                    this.builders = Collections.unmodifiableMap(builders.toMap())
                }
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Any> T.inject() {
    val injectorBuilder = Tanto.builders[javaClass] as? InjectorBuilder<T>
        ?: throw IllegalStateException("Can't find suitable ${InjectorBuilder::class.java.simpleName} for $this")
    injectorBuilder.build(this).inject(this)
}
