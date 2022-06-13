package com.axt.agenda

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.axt.agenda.CalendarUtils.selectedDate
import java.time.LocalTime
import java.time.format.TextStyle
import java.util.*
import kotlin.collections.ArrayList

class DailyCalendarActivity : AppCompatActivity() {
    private var monthDayText: TextView? = null
    private var dayOfWeekTV: TextView? = null
    private var hourListView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_calendar)
        initWidgets()
    }

    private fun initWidgets() {
        monthDayText = findViewById(R.id.monthDayText)
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV)
        hourListView = findViewById(R.id.hourListView)
    }

    override fun onResume() {
        super.onResume()
        setDayView()
    }

    private fun setDayView() {
        monthDayText?.text = selectedDate.let { CalendarUtils.monthDayFromDate(it) }
        var dayOfWeek: String? = selectedDate.dayOfWeek?.getDisplayName(TextStyle.FULL, Locale.getDefault())
        dayOfWeekTV?.text =dayOfWeek
        setHourAdapter()
    }

    private fun setHourAdapter() {
        val hourAdapter = HourAdapter(applicationContext, hourEventList())
        hourListView!!.adapter = hourAdapter
    }

    private fun hourEventList(): ArrayList<HourEvent?> {
        val list = ArrayList<HourEvent?>()
        for (hour in 0..24) {
            val time = LocalTime.of(hour, 0)
            val events: ArrayList<Event> =
                selectedDate.let { Event.eventsForDateAndTime(it, time) }
            val hourEvent = HourEvent(time, events)
            list.add(hourEvent)
        }
        return list
    }

    fun previousDayAction(view: View?) {
        selectedDate = selectedDate.minusDays(1)
        setDayView()
    }



    fun nextDayAction(view: View?) {
        selectedDate = selectedDate.plusDays(1)
        setDayView()
    }

    fun newEventAction(view: View?) {
        startActivity(Intent(this, EventEditActivity::class.java))
    }

}