package com.lifesyncpro.app.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

class ListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromIntList(value: List<Int>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIntList(value: String): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromFloatList(value: List<Float>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toFloatList(value: String): List<Float> {
        val listType = object : TypeToken<List<Float>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromMap(value: Map<String, Float>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMap(value: String): Map<String, Float> {
        val mapType = object : TypeToken<Map<String, Float>>() {}.type
        return gson.fromJson(value, mapType)
    }
} 