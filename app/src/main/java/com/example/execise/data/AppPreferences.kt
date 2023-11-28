package com.example.execise.data

import android.app.Activity
import android.content.Context

class AppPreferences(context: Activity) {
    private val preference = context.getSharedPreferences("name", Context.MODE_PRIVATE)

}