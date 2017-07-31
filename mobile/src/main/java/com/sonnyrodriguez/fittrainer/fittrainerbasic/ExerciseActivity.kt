package com.sonnyrodriguez.fittrainer.fittrainerbasic

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.ExerciseActivityUi
import dagger.android.AndroidInjection
import javax.inject.Inject

class ExerciseActivity: AppCompatActivity(), ExercisePresenter {
    private lateinit var ui: ExerciseActivityUi

    @Inject lateinit var exerciseHelper: ExercisePresenterHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View {
        return super.onCreateView(parent, name, context, attrs)
    }

    /*
        @Inject lateinit var presenter: ToDoPresenter

    var taskET: EditText? = null
    var addBtn: Button? = null
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)
        taskET = findViewById(R.id.task_et) as EditText
        addBtn = findViewById(R.id.add_btn) as Button
        recyclerView = findViewById(R.id.tasks_rv) as RecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = TaskAdapter(emptyList())

        addBtn?.setOnClickListener({ presenter.addNewTask(taskET?.text.toString()) })

        presenter.onCreate(this)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showTasks(tasks: List<Task>) {
        recyclerView?.adapter = TaskAdapter(tasks)
    }

    override fun taskAddedAt(position: Int) {
        recyclerView?.adapter?.notifyItemInserted(position)
    }

    override fun scrollTo(position: Int) {
        recyclerView?.smoothScrollToPosition(position)
    }
     */

}

/*
    class ToDoActivity : AppCompatActivity(), ToDoPresentation {

    @Inject lateinit var presenter: ToDoPresenter

    var taskET: EditText? = null
    var addBtn: Button? = null
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)
        taskET = findViewById(R.id.task_et) as EditText
        addBtn = findViewById(R.id.add_btn) as Button
        recyclerView = findViewById(R.id.tasks_rv) as RecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView?.adapter = TaskAdapter(emptyList())

        addBtn?.setOnClickListener({ presenter.addNewTask(taskET?.text.toString()) })

        presenter.onCreate(this)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showTasks(tasks: List<Task>) {
        recyclerView?.adapter = TaskAdapter(tasks)
    }

    override fun taskAddedAt(position: Int) {
        recyclerView?.adapter?.notifyItemInserted(position)
    }

    override fun scrollTo(position: Int) {
        recyclerView?.smoothScrollToPosition(position)
    }
}
 */
