package com.example.mealarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import java.util.*

class AlarmActivity : AppCompatActivity() {

    lateinit var am : AlarmManager
    lateinit var tp : TimePicker
    lateinit var update_text : TextView
    lateinit var con : Context
    lateinit var btn_set : Button
    lateinit var btn_stop : Button
    var hour = 0
    var min = 0
    lateinit var pi : PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        supportActionBar?.setTitle(getString(R.string.alarm_text))

        this.con = this
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        tp = findViewById(R.id.tp) as TimePicker
        update_text = findViewById(R.id.showTime_id) as TextView
        btn_set = findViewById(R.id.set_id) as Button
        btn_stop = findViewById(R.id.stop_id) as Button
        var calendar = Calendar.getInstance()
        var myIntent : Intent = Intent(this,AlarmReciever::class.java)
        btn_set.setOnClickListener(object  : View.OnClickListener{
            override fun onClick(v: View?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    calendar.set(Calendar.HOUR_OF_DAY,tp.hour)
                    calendar.set(Calendar.MINUTE,tp.minute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour = tp.hour
                    min = tp.minute
//                    Toast.makeText(this@AlarmActivity,">=",Toast.LENGTH_SHORT).show()
                } else {
                    calendar.set(Calendar.HOUR_OF_DAY,tp.currentHour)
                    calendar.set(Calendar.MINUTE,tp.currentMinute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour = tp.currentHour
                    min = tp.currentMinute
//                    Toast.makeText(this@AlarmActivity,"nooo",Toast.LENGTH_SHORT).show()
                }

                var hr_str : String = hour.toString()
                var min_str : String = min.toString()
                if (hour > 12){
                    hr_str = (hour - 12).toString()
                }
                if (min > 60){
                    min_str = "0$min"
                }
                set_alarm_text("${getText(R.string.alarm_set)} $hr_str ${getText(R.string.and)} $min_str ${getText(R.string.minutes)}")
//                Toast.makeText(applicationContext,"${getText(R.string.alarm_set)}: $hr_str : $min_str", Toast.LENGTH_SHORT).show()
                myIntent.putExtra("extra" , "on")

                pi = PendingIntent.getBroadcast(this@AlarmActivity,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT)
                am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pi) // الفانكشن دي بتحدد موعد المنبه بالظبط
            }
        })


        btn_stop.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(applicationContext,getText(R.string.alarm_off), Toast.LENGTH_SHORT).show()
                set_alarm_text(" ")
                pi = PendingIntent.getBroadcast(this@AlarmActivity,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT)
                am.cancel(pi)
                myIntent.putExtra("extra" , "off")
                sendBroadcast(myIntent)
            }
        })
    }

    private fun set_alarm_text(s: String) {
        update_text.setText(s)
    }


    fun scheduleClick(view: View) {}

}
