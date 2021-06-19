package io.github.kdk96.tanto.core

import java.util.Collections

public typealias InjectorBuildersProvider = Map<Class<*>, InjectorBuilder<*>>

public object Tanto {

    @Volatile
    public lateinit var builders: InjectorBuildersProvider
        private set

    public fun initBuilders(builders: InjectorBuildersProvider) {
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
public fun <T : Any> T.inject() {
    val injectorBuilder = Tanto.builders[javaClass] as? InjectorBuilder<T>
        ?: throw IllegalStateException("Can't find suitable ${InjectorBuilder::class.java.simpleName} for $this")
    injectorBuilder.build(this).inject(this)
}
