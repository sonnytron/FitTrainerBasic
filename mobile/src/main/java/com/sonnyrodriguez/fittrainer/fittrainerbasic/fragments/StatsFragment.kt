package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.HistoryAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutHistoryObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalStatObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.HistoryPresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.HistoryPresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.StatsFragmentUi
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class StatsFragment: Fragment(), HistoryPresenter {

    internal var historyAdapter = HistoryAdapter()
    @Inject lateinit var historyPresenter: HistoryPresenterHelper
    lateinit var ui: StatsFragmentUi

    companion object {
        fun newInstance() = StatsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        historyPresenter.onCreate(this)
        ui = StatsFragmentUi(historyAdapter)
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            historyPresenter.loadAllHistory()
        }
    }

    override fun loadAllHistory(historyObjects: List<WorkoutHistoryObject>) {
        val statList: ArrayList<LocalStatObject> = arrayListOf()
        historyObjects.forEach {
            LocalStatObject(title = LocalStatObject.totalExercisesDone(it),
                    dateString = LocalStatObject.dateCompleteString(it),
                    totalExercisesString = LocalStatObject.totalCount(it),
                    muscleGroups = LocalStatObject.musclesString(it),
                    durationString = LocalStatObject.durationString(it))
                    .apply {
                        statList.add(this)
                    }
        }
        ui.historyAdapter.addAll(statList)
    }

    override fun historySaved() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
