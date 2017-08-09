package com.sonnyrodriguez.fittrainer.fittrainerbasic.library

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_POSITION
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R

fun RecyclerView.ViewHolder.setOnItemClickedListener(listener: OnItemClickedListener) {
    this.itemView.setOnClickListener {
        if (this.adapterPosition != NO_POSITION) {
            listener.onItemClicked(this.adapterPosition)
        }
    }
}

fun Fragment.addFragment(fragment: Fragment) {
    fragmentManager.beginTransaction()
            .add(R.id.container, fragment, fragment::class.java.simpleName)
            .addToBackStack(fragment::class.java.simpleName)
            .hide(this)
            .commit()
}

fun Fragment.addFragment(fragment: Fragment, requestCode: Int) {
    fragment.setTargetFragment(this, requestCode)
    addFragment(fragment)
}
