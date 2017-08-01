package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import org.jetbrains.anko.*

class ExerciseItemUi: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = dimen(R.dimen.single_list_item_default_height)) {
                horizontalMargin = dip(16)
            }
            textView(R.style.BasicListItemStyle) {
                id = R.id.exercise_item_title
            }.lparams(width = matchParent, height = matchParent) {
                leftMargin = dip(12)
                gravity = Gravity.CENTER
            }
        }
    }
}
