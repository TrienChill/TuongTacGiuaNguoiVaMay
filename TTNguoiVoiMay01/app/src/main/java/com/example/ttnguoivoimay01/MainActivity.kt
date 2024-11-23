package com.example.ttnguoivoimay01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var btnSelectDateTime: Button
    private lateinit var tvSelectedDateTime: TextView
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectDateTime = findViewById(R.id.btnSelectDateTime)
        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime)

        btnSelectDateTime.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            R.style.MaterialCalendarTheme,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                showTimePicker()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val timePickerDialog = TimePickerDialog(
            this,
            R.style.MaterialTimePickerTheme,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                updateSelectedDateTime()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    private fun updateSelectedDateTime() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("vi"))
        val timeFormat = SimpleDateFormat("HH:mm", Locale("vi"))

        val dateStr = dateFormat.format(calendar.time)
        val timeStr = timeFormat.format(calendar.time)

        tvSelectedDateTime.text = "Bạn đã chọn lịch nhắc nhở vào:\n\nNgày: $dateStr\nGiờ: $timeStr"
        tvSelectedDateTime.visibility = View.VISIBLE
    }
}