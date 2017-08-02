package com.sonnyrodriguez.fittrainer.fittrainerbasic.library

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

abstract class GymAdapter<E> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val internalItemList = ArrayList<E>()
    internal var clickListener: OnItemClickedListener? = null

    val isEmpty: Boolean
        get() = internalItemList.isEmpty()

    val isNotEmpty: Boolean
        get() = !isEmpty

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        clickListener?.let { listener ->
            holder.setOnItemClickedListener(listener)
        }
        onBindInternalItemView(holder, position)
    }

    fun setOnItemClickedListener(listener: (Int) -> Unit) {
        this.clickListener = object : OnItemClickedListener {
            override fun onItemClicked(position: Int) {
                listener(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return onCreateInternalItemViewHolder(parent, viewType)
    }

    override fun getItemCount() = internalItemList.size

    fun add(index: Int, item: E) {
        internalItemList.add(index, item)
        notifyItemInserted(index)
    }

    fun add(item: E, notifyItem: Boolean = true) {
        internalItemList.add(item)
        if (notifyItem) {
            notifyItemInserted(itemCount)
        }
    }

    fun set(item: E, index: Int) {
        internalItemList[index] = item
        notifyItemChanged(index)
    }

    fun addAll(itemList: List<E>) {
        internalItemList.addAll(itemList)
        if (isEmpty) {
            notifyDataSetChanged()
        } else {
            notifyItemInserted(itemCount - itemList.size)
        }
    }

    fun clearAll() {
        internalItemList.clear()
        notifyDataSetChanged()
    }

    fun getInternalItem(position: Int) = internalItemList[position]

    fun clearItem(item: E, notifyRemoved: Boolean = true) {
        val clearPosition = indexOf(item)
        internalItemList.remove(item)
        if (notifyRemoved) {
            notifyItemRemoved(clearPosition)
        }
    }

    fun changeAll(itemList: List<E>) {
        internalItemList.clear()
        internalItemList.addAll(itemList)
        notifyDataSetChanged()
    }

    fun indexOf(item: E) = internalItemList.indexOf(item)

    protected abstract fun onBindInternalItemView(viewHolder: RecyclerView.ViewHolder, position: Int)
    protected abstract fun onCreateInternalItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
}
