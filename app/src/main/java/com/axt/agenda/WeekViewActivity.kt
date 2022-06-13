package com.axt.agenda

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axt.agenda.CalendarUtils.daysInWeekArray
import com.axt.agenda.CalendarUtils.monthYearFromDate
import java.time.LocalDate

abstract class WeekViewActivity : AppCompatActivity(), AdapterView.OnItemClickListener,
    CalendarAdapter.OnItemListener {
    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
    private var eventListView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_view)
        initWidgets()
        setWeekView()
    }

    private fun initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarMainRecyclerView)
        monthYearText = findViewById(R.id.monthYearTV)
        eventListView = findViewById(R.id.eventListView)
    }


    private fun setWeekView() {
        monthYearText?.text ?:  monthYearFromDate(CalendarUtils.selectedDate)
        val days: ArrayList<LocalDate> = daysInWeekArray()
        val calendarAdapter = CalendarAdapter(days, this)
        calendarRecyclerView?.layoutManager = GridLayoutManager(this, 7)
        calendarRecyclerView?.adapter = calendarAdapter
        setEventAdapter()
    }

    fun previousWeekAction(view: View) {
        CalendarUtils.selectedDate =
            CalendarUtils.selectedDate.minusWeeks(1)
        setWeekView()
    }

    fun nextWeekAction(view: View) {
        CalendarUtils.selectedDate =
            CalendarUtils.selectedDate.plusWeeks(1)
        setWeekView()
    }

    override fun onResume() {
        super.onResume()
        setEventAdapter()
    }

    private fun setEventAdapter() {
        val dailyEvents: ArrayList<Event> =
            CalendarUtils.selectedDate.let { Event.eventsForDate(it) }
        val eventAdapter = EventAdapter(applicationContext, dailyEvents)
        eventListView!!.adapter = eventAdapter
    }

    fun newEventAction(view: View) {
        startActivity(Intent(this, EventEditActivity::class.java))
    }

    fun dailyAction(view: View) {
        startActivity(Intent(this, DailyCalendarActivity::class.java))
    }

    override fun onItemClick(position: Int, date: LocalDate) {
        CalendarUtils.selectedDate = date
        setWeekView()
    }
}
