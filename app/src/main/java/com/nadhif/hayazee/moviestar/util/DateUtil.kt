package com.nadhif.hayazee.moviestar.util

import android.content.Context
import java.text.SimpleDateFormat

object DateUtil {
    fun convertDate(
        myDate: String
    ): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val newSdf = SimpleDateFormat("dd MMM yyyy")
        val date = sdf.parse(myDate) ?: return ""
        return newSdf.format(date)

    }
}