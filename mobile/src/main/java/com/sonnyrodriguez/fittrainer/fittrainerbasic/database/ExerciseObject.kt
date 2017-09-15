package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.*
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "exercise")
data class ExerciseObject(@ColumnInfo(name = "exercise_title") var title: String,
                          @ColumnInfo(name = "exercise_muscle_group") var muscleGroupNumber: Int,
                          @ColumnInfo(name = "exercise_image_list") var imageList: List<String>): Parcelable {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.createStringArrayList()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeInt(muscleGroupNumber)
        writeStringList(imageList)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ExerciseObject> = object : Parcelable.Creator<ExerciseObject> {
            override fun createFromParcel(source: Parcel): ExerciseObject = ExerciseObject(source)
            override fun newArray(size: Int): Array<ExerciseObject?> = arrayOfNulls(size)
        }
    }
}
