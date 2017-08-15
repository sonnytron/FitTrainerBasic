package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.view.Gravity
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import org.jetbrains.anko.*

class ExerciseCountItemUi: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = dimen(R.dimen.single_list_item_default_height)) {
                horizontalMargin = dip(8)
            }
            linearLayout {
                verticalLayout {
                    textView {
                        id = R.id.exercise_count_item_title
                    }.lparams(width = wrapContent, height = wrapContent) {
                        bottomMargin = dip(8)
                    }
                    textView {
                        id = R.id.exercise_count_item_muscle
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = wrapContent, height = matchParent) {
                    gravity = Gravity.CENTER_VERTICAL
                    horizontalMargin = dip(8)
                }
                textView {
                    id = R.id.exercise_count_item_count
                }.lparams(width = wrapContent, height = matchParent) {
                    gravity = Gravity.CENTER_VERTICAL
                    rightMargin = dip(16)
                }
            }.lparams(width = matchParent, height = matchParent) {
                centerInParent()
            }
        }
    }
}