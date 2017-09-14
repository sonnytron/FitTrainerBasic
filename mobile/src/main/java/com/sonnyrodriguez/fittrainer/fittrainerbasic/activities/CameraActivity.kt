package com.sonnyrodriguez.fittrainer.fittrainerbasic.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import com.flurgle.camerakit.CameraListener
import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import com.flurgle.camerakit.CameraView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.file.PhotoFileManager
import org.jetbrains.anko.find

class CameraActivity: AppCompatActivity() {

    var fileManager: PhotoFileManager? = null

    var exerciseId: Long = 0L
    var exerciseIndex: Int = 0

    val exercisePhotoPath: String by lazy {
        val externalDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (externalDirectory == null) {
            return@lazy filesDir.absolutePath
        } else {
            return@lazy externalDirectory.absolutePath
        }
    }

    var camera: CameraView? = null
    lateinit var shutterButton: ImageButton
    lateinit var flashButton: ImageButton
    var cameraListener = object : CameraListener() {
        override fun onPictureTaken(jpeg: ByteArray?) {
            super.onPictureTaken(jpeg)
            jpeg?.let {
                val rawBitmap: Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            }
        }
    }

    companion object {
        fun newIntent(exerciseId: Long, index: Int): Intent {
            val intent = Intent(FitTrainerApplication.instance, CameraActivity::class.java)
            intent.putExtra(KeyConstants.INTENT_EXERCISE_ID, exerciseId)
            intent.putExtra(KeyConstants.INTENT_EXERCISE_INDEX, index)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.camera_activity)
        exerciseId = intent.getLongExtra(KeyConstants.INTENT_EXERCISE_ID, 0L)
        exerciseIndex = intent.getIntExtra(KeyConstants.INTENT_EXERCISE_INDEX, 0)
        initializeCamera()
    }

    internal fun initializeCamera() {
        camera = find(R.id.camera_object)
        shutterButton = find(R.id.camera_activity_shutter)
        flashButton = find(R.id.camera_activity_flash)

        fileManager = PhotoFileManager(exercisePhotoPath)
        camera?.start()
        camera?.setCameraListener(cameraListener)
    }

    override fun onResume() {
        super.onResume()
        camera?.start()
    }

    override fun onPause() {
        super.onPause()
        camera?.stop()
    }
}
