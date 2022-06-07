package com.kotlindev.newsforyou.ui.db

import androidx.room.TypeConverter
import com.kotlindev.newsforyou.ui.models.Source

class Converters {

    @TypeConverter
    //source to string
    //SQLite db does not support source. so we convert it to String
    fun fromSource(source : Source):String {
        return source.name
    }
    //string to source
    @TypeConverter
    fun toSource(name :String): Source {
        return Source(name,name)
    }
}