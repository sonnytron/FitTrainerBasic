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
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.file.PhotoFileManager
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.SingleExercisePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.UIConstants
import dagger.android.AndroidInjection
import org.jetbrains.anko.find
import javax.inject.Inject

class CameraActivity: AppCompatActivity(), SingleExercisePresenter {

    var fileManager: PhotoFileManager? = null
    var exerciseSavedString: String? = null
    var localImages: ArrayList<String> = arrayListOf()

    lateinit var localExerciseObject: ExerciseObject

    var exerciseId: Long = 0L
    var exerciseIndex: Int = 0

    @Inject lateinit var exercisePresenterHelper: ExercisePresenterHelper

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
                fileManager?.let { fileMan ->
                    fileMan.ensurePhotoPath().apply {
                        fileMan.createImagePath(rawBitmap, filePathString()).apply {
                            setValueForResult(this)
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun newIntent(exerciseObject: ExerciseObject): Intent {
            val intent = Intent(FitTrainerApplication.instance, CameraActivity::class.java)
            intent.putExtra(KeyConstants.INTENT_EXERCISE_INDEX, exerciseObject)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.camera_activity)
        localExerciseObject = intent.getParcelableExtra(KeyConstants.INTENT_EXERCISE_INDEX)
        initializeCamera()
    }

    internal fun initializeCamera() {
        camera = find(R.id.camera_object)
        shutterButton = find(R.id.camera_activity_shutter)
        flashButton = find(R.id.camera_activity_flash)
        localImages.addAll(localExerciseObject.imageList)
        fileManager = PhotoFileManager(exercisePhotoPath)
        camera?.start()
        camera?.setCameraListener(cameraListener)
        exercisePresenterHelper.onCreate(this)
    }

    override fun onResume() {
        super.onResume()
        camera?.start()
    }

    override fun onPause() {
        super.onPause()
        camera?.stop()
    }

    internal fun setValueForResult(valueString: String) {
        localImages.add(valueString)
        localExerciseObject.imageList = localImages
        exercisePresenterHelper.saveSingleExercise(localExerciseObject)
    }

    internal fun filePathString(): String =
            "$exerciseId${UIConstants.DEFAULT_FILE_PATH_VALUE_BAR}$exerciseIndex${UIConstants.DEFAULT_FILE_PATH_VALUE_BAR}${UIConstants.FILE_INTERMEDIATE_NAME}"

    override fun singleExerciseSaved() {
        finish()
    }
}
