package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import org.jetbrains.anko.*

class HistoryItemUi: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        relativeLayout {
            backgroundDrawable = ContextCompat.getDrawable(ctx, R.drawable.list_background_white)
            lparams(width = matchParent, height = dimen(R.dimen.single_list_item_default_height)) {
                horizontalMargin = dip(16)
                topMargin = dip(6)
            }
            verticalLayout {
                themedTextView(R.style.BasicListItemTitle) {
                    id = R.id.history_item_title
                }.lparams(width = wrapContent, height = wrapContent) {
                    bottomMargin = dip(8)
                }

                linearLayout {
                    themedTextView(R.style.BasicListItemStyle) {
                        id = R.id.history_item_completed
                    }.lparams(width = wrapContent, height = wrapContent) {
                        horizontalMargin = dip(6)
                    }

                    themedTextView(R.style.BasicListItemStyle) {
                        id = R.id.history_item_count
                    }.lparams(width = wrapContent, height = wrapContent) {
                        horizontalMargin = dip(6)
                    }

                    themedTextView(R.style.BasicListItemStyle) {
                        id = R.id.history_item_duration
                    }.lparams(width = wrapContent, height = wrapContent) {
                        gravity = Gravity.END
                    }
                }
            }
        }
    }
}
