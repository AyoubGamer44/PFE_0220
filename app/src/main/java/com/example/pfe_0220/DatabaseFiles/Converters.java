package com.example.pfe_0220.DatabaseFiles;

import androidx.room.TypeConverter;

import java.util.Calendar;
import java.util.Date;

public class Converters {


    @TypeConverter
    public static Calendar fromTimestamp(Long value) {

        Date day = value == null ? null : new Date(value);
        Calendar c_day = Calendar.getInstance();
        c_day.setTime(day);
        return value == null ? null : c_day;
    }

    @TypeConverter
    public static Long dateToTimestamp(Calendar day) {
        Date date = day.getTime();
        return date == null ? null : date.getTime();
    }

}
