package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.layout

import android.content.Context
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko._LinearLayout

class StatusLayout(ctx: Context) : _LinearLayout(ctx) {
    lateinit var muscleGroupTextView: TextView
    lateinit var muscleCountText: TextView
    lateinit var exerciseTitleText: TextView

    lateinit var startAction: Button


}
