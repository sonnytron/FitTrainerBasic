package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import org.jetbrains.anko.*

class ExerciseCountItemUi: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        relativeLayout {
            backgroundDrawable = ContextCompat.getDrawable(ctx, R.drawable.list_background_white)
            lparams(width = matchParent, height = wrapContent) {
                horizontalMargin = dip(8)
                verticalPadding = dip(16)
            }
            linearLayout {
                verticalLayout {
                    themedTextView(R.style.BasicListItemTitle) {
                        id = R.id.exercise_count_item_title
                    }.lparams(width = wrapContent, height = wrapContent) {
                        bottomMargin = dip(8)
                    }
                    themedTextView(R.style.BasicListItemStyle) {
                        id = R.id.exercise_count_item_muscle
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = wrapContent, height = matchParent) {
                    gravity = Gravity.CENTER_VERTICAL
                    horizontalMargin = dip(8)
                }
                themedTextView(R.style.BasicListItemTitle) {
                    id = R.id.exercise_count_item_count
                }.lparams(width = wrapContent, height = matchParent) {
                    gravity = Gravity.END
                    topPadding = dip(8)
                    rightMargin = dip(16)
                }
            }.lparams(width = matchParent, height = matchParent) {
                centerInParent()
            }
        }
    }
}
