package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.*
import android.os.Parcel
import android.os.Parcelable
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject

@Entity(tableName = "workouts")
data class WorkoutObject(@ColumnInfo(name = "workout_title") var title: String,
                         @ColumnInfo(name = "exercise_meta_list") var exerciseMetaList: List<LocalExerciseObject>) : Parcelable {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    constructor(source: Parcel) : this(
            source.readString(),
            source.createTypedArrayList(LocalExerciseObject.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeTypedList(exerciseMetaList)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<WorkoutObject> = object : Parcelable.Creator<WorkoutObject> {
            override fun createFromParcel(source: Parcel): WorkoutObject = WorkoutObject(source)
            override fun newArray(size: Int): Array<WorkoutObject?> = arrayOfNulls(size)
        }
    }
}
