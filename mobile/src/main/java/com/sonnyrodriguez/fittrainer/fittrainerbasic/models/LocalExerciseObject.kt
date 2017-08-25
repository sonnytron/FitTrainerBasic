package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import android.os.Parcel
import android.os.Parcelable

data class LocalExerciseObject(val title: String, val count: Long, val set: Long, val exerciseId: Long, val muscleGroup: Int) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readLong(),
            source.readLong(),
            source.readLong(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeLong(count)
        writeLong(set)
        writeLong(exerciseId)
        writeInt(muscleGroup)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<LocalExerciseObject> = object : Parcelable.Creator<LocalExerciseObject> {
            override fun createFromParcel(source: Parcel): LocalExerciseObject = LocalExerciseObject(source)
            override fun newArray(size: Int): Array<LocalExerciseObject?> = arrayOfNulls(size)
        }
    }
}
