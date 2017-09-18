package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.activities.CameraActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.file.PhotoFileManager
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleEnum
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.SingleExercisePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.EditExerciseFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class EditExerciseFragment: Fragment(), SingleExercisePresenter {

    lateinit var ui: EditExerciseFragmentUi
    lateinit var muscleAdapter: ArrayAdapter<MuscleEnum>
    internal var internalExerciseObject: ExerciseObject? = null
    var fileManager: PhotoFileManager? = null
    var imageList: ArrayList<String> = arrayListOf()

    @Inject lateinit var exercisePresenterHelper: ExercisePresenterHelper

    val exercisePhotoPath: String by lazy {
        val externalDirectory = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (externalDirectory == null) {
            return@lazy activity.filesDir.absolutePath
        } else {
            return@lazy externalDirectory.absolutePath
        }
    }

    companion object {
        fun newInstance(exerciseObject: ExerciseObject? = null) = EditExerciseFragment().apply {
            exerciseObject?.let {
                internalExerciseObject = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        fileManager = PhotoFileManager(exercisePhotoPath)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        exercisePresenterHelper.onDestroy()
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = EditExerciseFragmentUi()
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            attachMuscleAdapter()
            internalExerciseObject?.let {
                for (i in 0..muscleAdapter.count - 1) {
                    muscleAdapter.getItem(i).let { muscleEnum ->
                        if (muscleEnum.ordinal == it.muscleGroupNumber) {
                            ui.updateUi(it.title, muscleAdapter.getPosition(muscleEnum))
                        }
                    }
                }
                loadExerciseImages(it.imageList)
            }
            exercisePresenterHelper.onCreate(this@EditExerciseFragment)
        }
    }

    internal fun loadExerciseImages(imageList: List<String>) {
        fileManager?.let { fileMan ->
            imageList.forEachIndexed { index, imageString ->
                fileMan.imageStream(imageString)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { image ->
                            when (index) {
                                1 -> {
                                    ui.firstImage.setImageBitmap(image)
                                }
                                2 -> {
                                    ui.secondImage.setImageBitmap(image)
                                }
                                else -> {
                                    ui.thirdImage.setImageBitmap(image)
                                }
                            }
                        }
            }
        }
    }

    internal fun attachMuscleAdapter() {
        muscleAdapter = ArrayAdapter(ctx, android.R.layout.simple_list_item_1, MuscleEnum.values()).apply {
            ui.muscleSpinner.adapter = this
        }
    }

    internal fun saveExercise() {
        if (internalExerciseObject == null) {
            ExerciseObject(ui.exerciseTitleEdit.text.toString(), muscleAdapter.getItem(ui.muscleSpinner.selectedItemPosition).ordinal, imageList).apply {
                exercisePresenterHelper.addNewExercise(this)
            }
        } else {
            internalExerciseObject?.let {
                exercisePresenterHelper.saveSingleExercise(it)
            }
        }
    }

    override fun singleExerciseSaved() {
        targetFragment?.let { targetFrag ->
            val intent = Intent()
            intent.putExtra(KeyConstants.KEY_RESULT_BOOLEAN, true)
            targetFrag.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            fragmentManager.popBackStack()
        }
    }

    internal fun openCamera() {
        internalExerciseObject?.let {
            CameraActivity.newIntent(it).apply {
                startActivity(this)
            }
        }
    }
}
