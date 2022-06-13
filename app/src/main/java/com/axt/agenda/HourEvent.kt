package com.axt.agenda

import java.time.LocalTime

internal class HourEvent(var time: LocalTime, var events: ArrayList<Event>) {
    @JvmName("getEvents1")
    fun getEvents(): ArrayList<Event> {
        return events
    }

    @JvmName("setEvents1")
    fun setEvents(events: ArrayList<Event>) {
        this.events = events
    }

    init {
    }
}