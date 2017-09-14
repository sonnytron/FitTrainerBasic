package com.sonnyrodriguez.fittrainer.fittrainerbasic.file

import android.graphics.Bitmap
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.UIConstants
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

open class PhotoFileManager(val root: String) {
    val photoFilePath: String
        get() = "$root${UIConstants.FILE_PATH_SEPARATOR}photos"

    fun createImagePath(bitmap: Bitmap, title: String): String {
        val tempImageFile = File(photoFilePath, "$title${UIConstants.FILE_INTERMEDIATE_NAME}").apply {
            FileOutputStream(this.absolutePath).use { outputStream ->
                bitmap.compress(
                        Bitmap.CompressFormat.PNG,
                        UIConstants.DEFAULT_IMAGE_QUALITY,
                        outputStream)
            }
        }
        return tempImageFile.absolutePath
    }

    fun ensurePhotoPath() {
        ensureFolderExists(photoFilePath)
    }

    private fun ensureFolderExists(folderPath: String) {
        val path = File(folderPath)
        if (!path.exists()) {
            if (!path.mkdir()) {
                throw IOException("Cannot create $folderPath path")
            }
        }
    }
}
