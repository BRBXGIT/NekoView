package com.example.common.dispatchers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val aniKunDispatcher: AniKunDispatchers)

enum class AniKunDispatchers {
    Default,
    IO,
}