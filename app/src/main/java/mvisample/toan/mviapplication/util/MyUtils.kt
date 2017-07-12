package mvisample.toan.mviapplication.util

import android.annotation.SuppressLint

import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * Created by Toan Vu on 6/1/16.
 */
class MyUtils {
    companion object {
        @SuppressLint("SimpleDateFormat")
        fun getDayQuery(day: String): String {
            val dayInt = day.toInt()
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -dayInt)
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return "created:>=" + sdf.format(cal.time)
        }
    }
}
