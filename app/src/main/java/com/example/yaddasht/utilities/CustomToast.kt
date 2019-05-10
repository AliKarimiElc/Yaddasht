package com.example.yaddasht.utilities

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.yaddasht.R

class CustomToast{

companion object {
    fun toast(context: Context, toastDuration: Int, gravity: Int, message: String) {
        val layout = LinearLayout.inflate(
            context,
            R.layout.custom_toast,
            (context as Activity).findViewById(R.id.custom_toast_container)
        )
        val text: TextView = layout.findViewById(R.id.text_toast)
        text.text = message
        with(Toast(context)) {
            setGravity(gravity, 0, 0)
            duration = toastDuration
            view = layout
            show()
        }
    }


    fun toast(context: Context, toastDuration: Int, message: String) {
        val layout = LinearLayout.inflate(
            context,
            R.layout.custom_toast,
            (context as Activity).findViewById(R.id.custom_toast_container)
        )
        val text: TextView = layout.findViewById(R.id.text_toast)
        text.text = message
        with(Toast(context)) {
            setGravity(Gravity.CENTER, 0, 0)
            duration = toastDuration
            view = layout
            show()
        }
    }
}
}