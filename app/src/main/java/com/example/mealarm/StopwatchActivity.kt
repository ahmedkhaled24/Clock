package com.example.mealarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_stopwatch.*

class StopwatchActivity : AppCompatActivity() {

    var pauseAt : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)

        supportActionBar?.setTitle(getString(R.string.stop_watch_text))

        btnStart_id.setOnClickListener {
            timeStopWatch_id.base = SystemClock.elapsedRealtime()-pauseAt // 6000 = 1min
            timeStopWatch_id.start()

            btnStart_id.visibility = View.GONE
            btnPause_id.visibility = View.VISIBLE
            btnReset_id.visibility = View.VISIBLE

            messageToast(getString(R.string.start_text))

        }

        btnPause_id.setOnClickListener{
            pauseAt = SystemClock.elapsedRealtime()-timeStopWatch_id.base // 10000-6000=4000
            timeStopWatch_id.stop()

            btnPause_id.visibility = View.GONE
            btnReset_id.visibility = View.GONE
            btnStart_id.visibility = View.VISIBLE

            messageToast(getString(R.string.pause_text))
        }

        btnReset_id.setOnClickListener {
            timeStopWatch_id.base = SystemClock.elapsedRealtime()
            timeStopWatch_id.stop()

            btnReset_id.visibility = View.GONE
            btnPause_id.visibility = View.GONE
            btnStart_id.visibility = View.VISIBLE

            messageToast(getString(R.string.reset_text))
        }
    }

    fun messageToast(s:String){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
    }
}
