package com.sonnyrodriguez.fittrainer.fittrainerbasic.library

import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_POSITION
import android.util.Log
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
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .addToBackStack(fragment::class.java.simpleName)
            .hide(this)
            .commit()
}

fun Fragment.addFragmentDSL(fragment: Fragment) {
    fragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment, fragment::class.java.simpleName)
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .addToBackStack(fragment::class.java.simpleName)
            .hide(this)
            .commit()
}

fun Fragment.replaceFragment(fragment: Fragment) {
    fragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment::class.java.simpleName)
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .hide(this)
            .commit()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment::class.java.simpleName)
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .commit()
}

fun AppCompatActivity.replaceFragmentDSL(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, fragment::class.java.simpleName)
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .commit()
}

fun Fragment.addFragment(fragment: Fragment, requestCode: Int) {
    fragment.setTargetFragment(this, requestCode)
    addFragment(fragment)
}

fun Fragment.addFragmentDSL(fragment: Fragment, requestCode: Int) {
    fragment.setTargetFragment(this, requestCode)
    addFragmentDSL(fragment)
}

fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0..menuView.childCount - 1) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        Log.e("BottomNavigationHelper", "Unable to get shift mode field", e)
    } catch (e: IllegalAccessException) {
        Log.e("BottomNavigationHelper", "Unable to change value of shift mode", e)
    }
}

fun BottomNavigationView.active(position: Int) {
    menu.getItem(position).isChecked = true
}
