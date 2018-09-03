package com.barbasdev.base.room;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.room.TypeConverter;

public class Converters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Long> stringToLongList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Long>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String longListToString(List<Long> someObjects) {
        return gson.toJson(someObjects);
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}