package com.axt.agenda

import java.time.LocalDate
import java.time.LocalTime

class Event {
    fun event(name: String, date: LocalDate, time: LocalTime) {
        this.name = name
        this.date = date
        this.time = time
    }

    private var name: String? = null
    private var date: LocalDate? = null
    private var time: LocalTime? = null


    @JvmName("getName1")
    fun getName(): String {
        return name!!
    }

    @JvmName("getDate1")
    fun getDate(): LocalDate {
        return date!!
    }

    @JvmName("getTime1")
    fun getTime(): LocalTime {
        return time!!
    }

    companion object {
        var eventsList = ArrayList<Event>()
        fun eventsForDate(date: LocalDate): ArrayList<Event> {
            val events = ArrayList<Event>()
            for (event in eventsList) {
                if (event.date == date) events.add(event)
            }
            return events
        }

        fun eventsForDateAndTime(date: LocalDate, time: LocalTime): ArrayList<Event> {
            val events = ArrayList<Event>()
            for (event in eventsList) {
                val eventHour = event.time!!.hour
                val cellHour = time.hour
                if (event.date == date && eventHour == cellHour) events.add(event)
            }
            return events
        }
        }
    }
