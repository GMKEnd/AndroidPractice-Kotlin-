package com.example.testproject

import android.content.Context

class DataSource(val context: Context) {
    fun getFlowerList(): Array<String> {
        return context.resources.getStringArray(R.array.test_array)
    }
}
