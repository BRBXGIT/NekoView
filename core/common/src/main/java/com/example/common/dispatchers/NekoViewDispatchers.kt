package com.example.common.dispatchers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val nekoViewDispatcher: NekoViewDispatchers)

enum class NekoViewDispatchers {
    Default,
    IO,
}