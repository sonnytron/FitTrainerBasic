package com.sonnyrodriguez.fittrainer.fittrainerbasic.activities

import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ExercisePhotoActivity: AppCompatActivity() {
    val CAMERA_REQUEST_CODE = 15
    lateinit var imageFilePath: String

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    @Throws(IOException::class)
    fun createImageFile(withExerciseId: String): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName: String = "Exercise_${withExerciseId}_${timeStamp}_"
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!storageDir.exists()) storageDir.mkdirs()
        val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir).apply {
            imageFilePath = this.absolutePath
        }
        return imageFile
    }
}
