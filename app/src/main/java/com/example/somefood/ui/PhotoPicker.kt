package com.example.somefood.ui


import android.content.Context
import android.content.ContextWrapper
import android.content.Intent.ACTION_PICK
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.somefood.app.App
import com.example.somefood.ui.profile.ProfileFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.sql.Wrapper
import java.util.*

class PhotoPicker(
    activityResultRegistry: ActivityResultRegistry,
    private val callback: (image: Uri?) -> Unit
) {
    private val getContentLauncher = activityResultRegistry.register(
        ACTION_PICK,
        ActivityResultContracts.GetContent()
    ) { uri -> callback.invoke(uri) }

    fun pickPhoto() {
        getContentLauncher.launch("image/*")
    }


}

