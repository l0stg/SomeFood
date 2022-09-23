package com.example.somefood.ui.Event

class Event<T>(val content: T) {

    private var handled = false

    fun content() = if (handled) {
        null
    } else {
        handled = true
        content
    }
}