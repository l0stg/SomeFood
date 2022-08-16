package com.example.somefood.ui

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

object Crypto {
    @RequiresApi(Build.VERSION_CODES.O)
    fun encode(input: String): String =
        Base64.getEncoder().encodeToString(input.toByteArray())
}