package com.example.mealarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun btnGoAlarm(view: View) {
        var i = Intent(this,AlarmActivity::class.java)
        startActivity(i)
    }

    fun btnGoStopWatch(view: View) {
        var i = Intent(this,StopwatchActivity::class.java)
        startActivity(i)
    }

}
