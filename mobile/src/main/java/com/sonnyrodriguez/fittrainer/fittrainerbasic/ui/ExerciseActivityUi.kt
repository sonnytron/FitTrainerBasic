package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ExerciseActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ExerciseActivityUi: AnkoComponent<ExerciseActivity> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit var editExerciseText: EditText
    lateinit var addExerciseButton: Button

    override fun createView(ui: AnkoContext<ExerciseActivity>) = with(ui) {
        relativeLayout {
            cardView {
                linearLayout {
                    editExerciseText = editText {

                    }

                    addExerciseButton = button {

                    }
                }
            }

            exerciseRecyclerView = recyclerView {

            }
        }
    }
}

/*
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context="com.manijshrestha.todolist.ui.ToDoActivity">

    <android.support.v7.widget.CardView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginEnd="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <EditText
                android:id="@+id/task_et"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_margin="8dp"
                android:layout_weight="1"
                android:hint="@string/task" />

            <Button
                android:id="@+id/add_btn"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:layout_marginEnd="8dp"
                android:text="@string/add" />
        </LinearLayout>
    </android.support.v7.widget.CardView>

    <android.support.v7.widget.RecyclerView
        android:id="@+id/tasks_rv"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:clipToPadding="false"
        android:paddingTop="72dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:listitem="@layout/item_to_do" />
 */