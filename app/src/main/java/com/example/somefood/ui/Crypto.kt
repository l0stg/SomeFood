package com.example.somefood.ui

import java.util.*

object Crypto {
    fun encode(input: String): String =
        Base64.getEncoder().encodeToString(input.toByteArray())
}