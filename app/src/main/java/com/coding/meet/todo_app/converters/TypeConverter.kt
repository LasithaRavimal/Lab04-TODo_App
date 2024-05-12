package com.coding.meet.todo_app.converters

import androidx.room.TypeConverter
import java.util.Date

class TypeConverter {

    // Convert a timestamp (Long) to a Date object
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    // Convert a Date object to a timestamp (Long)
    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}
