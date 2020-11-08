package ua.kpi.comsys.iv7124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

val LOG_TAG = "LAB2"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contents()

        val time0 = TimeOT()
        val time1 = TimeOT(13, 46, 57)
        val time2 = TimeOT(11, 10, 20)
        val time3 = TimeOT(1, 30, 90) // will become 01:30:00
        val currentTime = TimeOT(Date())

        Log.i(LOG_TAG, "time0 = $time0")
        Log.i(LOG_TAG, "time1 = $time1")
        Log.i(LOG_TAG, "time2 = $time2")
        Log.i(LOG_TAG, "time3 = $time3")
        Log.i(LOG_TAG, "currentTime = $currentTime")

        Log.i(LOG_TAG, "time1 + time3 = ${time1.add(time3)}")
        Log.i(LOG_TAG, "time2 - time3 = ${time2.sub(time3)}")
        Log.i(LOG_TAG, "time1 + time2 = ${addTimes(time1, time2)}")
        Log.i(LOG_TAG, "time0 - time2 = ${subTimes(time0, time2)}")
    }
}