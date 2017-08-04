package com.sonnyrodriguez.fittrainer.fittrainerbasic.models;

import android.arch.persistence.room.TypeConverter;

public enum MuscleGroup {
    CHEST(0), LEGS(1), ABS(2), BACK(3), SHOULDERS(4);

    private final int group;

    @TypeConverter
    public static MuscleGroup fromGroup(Integer groupNumber) {
        for (MuscleGroup m : values()) {
            if (m.group == groupNumber) {
                return(m);
            }
        }
        return(null);
    }

    @TypeConverter
    public static Integer fromMuscleGroup(MuscleGroup group) {
        return(group.group);
    }

    MuscleGroup(int groupNumber) {
        this.group = groupNumber;
    }
}