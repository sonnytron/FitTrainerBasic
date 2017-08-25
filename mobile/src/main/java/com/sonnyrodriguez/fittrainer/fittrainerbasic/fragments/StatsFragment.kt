package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.StatsFragmentUi
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class StatsFragment: Fragment() {
    lateinit var ui: StatsFragmentUi

    companion object {
        fun newInstance() = StatsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = StatsFragmentUi()
        return ui.createView(AnkoContext.Companion.create(ctx, this))
    }
}
