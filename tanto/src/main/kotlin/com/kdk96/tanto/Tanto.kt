package com.kdk96.tanto

import java.util.Collections

typealias InjectorBuildersProvider = Map<Class<*>, InjectorBuilder<*>>

typealias InjectorsProvider = MutableMap<String, Injector<*>>

object Tanto {

    @Volatile
    private lateinit var builders: InjectorBuildersProvider

    private val injectors: InjectorsProvider = mutableMapOf()

    fun initBuilders(builders: InjectorBuildersProvider) {
        if (this::builders.isInitialized) {
            throw IllegalStateException("builders have already been initialized")
        } else {
            synchronized(this) {
                this.builders = Collections.unmodifiableMap(builders.toMap())
            }
        }
    }

    fun <T : Any> T.inject() {
        @Suppress("UNCHECKED_CAST")
        val injectorBuilder = builders[javaClass] as? InjectorBuilder<T>
            ?: throw IllegalStateException("Can't find suitable ${InjectorBuilder::class.java.simpleName} for $this")
        injectorBuilder.build(this).inject(this)
    }

    @Suppress("UNCHECKED_CAST")
    @Synchronized
    fun <T : Any> T.inject(injectorName: String) {
        var injector = injectors[injectorName]
        if (injector == null) {
            val injectorBuilder = builders[javaClass] as? InjectorBuilder<T>
                ?: throw IllegalStateException(
                    "Can't find suitable ${InjectorBuilder::class.java.simpleName} for $this injectorName: $injectorName"
                )
            injector = injectorBuilder.build(this)
            injectors[injectorName] = injector
        }
        (injector as Injector<T>).inject(this)
    }

    @Synchronized
    fun clearInjector(injectorName: String) {
        injectors.remove(injectorName)
    }
}
