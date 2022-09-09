package com.example.todoapp.utils

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapterUtils {

    private const val COMPLETED = "Completed"
    private const val INCOMPLETE = "InComplete"

    @JvmStatic
    @BindingAdapter("app:taskStatus")
    fun taskStatus(view: TextView, status: Boolean) {
        view.text = if (status) COMPLETED else INCOMPLETE
        view.setTextColor(if (status) Color.GREEN else Color.RED)
    }
}