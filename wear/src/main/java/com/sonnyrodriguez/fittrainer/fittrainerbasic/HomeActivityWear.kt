package com.sonnyrodriguez.fittrainer.fittrainerbasic

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class HomeActivityWear : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_wear)

        // Enables Always-on
        setAmbientEnabled()
    }
}
