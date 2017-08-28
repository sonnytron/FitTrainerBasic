package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import org.jetbrains.anko.*

class WorkoutItemUi: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        relativeLayout {
            backgroundDrawable = ContextCompat.getDrawable(ctx, R.drawable.list_background_white)
            lparams(width = matchParent, height = dimen(R.dimen.single_list_item_default_height)) {
                horizontalMargin = dip(16)
                topMargin = dip(6)
            }
            verticalLayout {
                themedTextView(R.style.BasicListItemTitle) {
                    id = R.id.workout_item_title
                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                }
                themedTextView(R.style.BasicListItemStyle) {
                    id = R.id.workout_exercise_count
                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.BOTTOM
                }
            }
        }
    }
}
