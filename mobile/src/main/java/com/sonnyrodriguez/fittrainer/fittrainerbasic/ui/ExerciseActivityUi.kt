package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import com.sonnyrodriguez.fittrainer.fittrainerbasic.ExerciseActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationItemView
import org.jetbrains.anko.design.bottomNavigationMenuView

class ExerciseActivityUi: AnkoComponent<ExerciseActivity> {

    override fun createView(ui: AnkoContext<ExerciseActivity>) = with(ui) {
        relativeLayout {
            bottomNavigationMenuView {
                id = R.id.navigation_menu

            }
            bottomNavigationItemView {
                id = R.id.navigation_menu

            }.lparams(width = matchParent, height = wrapContent) {
                alignParentBottom()
            }
            frameLayout {
                id = R.id.container
            }.lparams(width = matchParent, height = matchParent) {
                above(R.id.navigation_menu)
            }

        }
    }
}
