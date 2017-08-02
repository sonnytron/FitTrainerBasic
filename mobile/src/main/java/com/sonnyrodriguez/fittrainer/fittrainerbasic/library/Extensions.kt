package com.sonnyrodriguez.fittrainer.fittrainerbasic.library

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_POSITION

fun RecyclerView.ViewHolder.setOnItemClickedListener(listener: OnItemClickedListener) {
    this.itemView.setOnClickListener {
        if (this.adapterPosition != NO_POSITION) {
            listener.onItemClicked(this.adapterPosition)
        }
    }
}
