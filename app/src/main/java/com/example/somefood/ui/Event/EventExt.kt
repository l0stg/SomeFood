package com.example.somefood.ui.Event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach

fun <T> Flow<Event<T>>.onEachEvent(action: suspend (T) -> Unit): Flow<Event<T>> = onEach {
    it.content()?.let { action(it) }
}

fun <T> Flow<Event<T>>.mapEvent(): Flow<T> = mapNotNull {
    it.content()
}