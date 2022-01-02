package com.mina_magid.benshty

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.varunbarad.highlightable_calendar_view.DayDecorator
import com.varunbarad.highlightable_calendar_view.HighlightableCalendarView
import com.varunbarad.highlightable_calendar_view.OnDateSelectListener
import com.varunbarad.highlightable_calendar_view.OnMonthChangeListener
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val calendarView: HighlightableCalendarView = this.findViewById(R.id.calendar)

        calendarView.dayDecorators = listOf(
            DayDecorator(
                Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, 4)
                },
                Color.parseColor("#ffffff"),
                R.drawable.mcv_action_next,
            ),
                    DayDecorator(
                Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, 20)
                },
                ContextCompat.getColor(this, R.color.white),
                ContextCompat.getColor(this, R.color.colorAccent)
            )
        )


        calendarView.onMonthChangeListener = OnMonthChangeListener { oldMonth, newMonth ->
            val oldMonthDisplay = oldMonth.getDisplayName(
                Calendar.MONTH,
                Calendar.LONG,
                Locale.getDefault()
            )
            val newMonthDisplay = newMonth.getDisplayName(
                Calendar.MONTH,
                Calendar.LONG,
                Locale.getDefault()
            )

            Toast.makeText(
                this,
                "$oldMonthDisplay -> $newMonthDisplay",
                Toast.LENGTH_SHORT
            ).show()
        }

        calendarView.onDateSelectListener = OnDateSelectListener { selectedDate ->
            val date = String.format("%02d", selectedDate.get(Calendar.DAY_OF_MONTH))
            val month = selectedDate.getDisplayName(
                Calendar.MONTH,
                Calendar.LONG,
                Locale.getDefault()
            )
            val year = selectedDate.get(Calendar.YEAR).toString()

            Toast.makeText(this, "$month $date, $year", Toast.LENGTH_SHORT).show()
        }
    }
}
