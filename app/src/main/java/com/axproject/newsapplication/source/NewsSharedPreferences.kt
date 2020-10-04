package com.axproject.newsapplication.source

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class NewsSharedPreferences(context: Context) {

    private lateinit var editor: SharedPreferences.Editor
    private var sharedPreferences: SharedPreferences =
        context.applicationContext.getSharedPreferences("news_prefs", MODE_PRIVATE)

    public fun saveContNews(count : Int) {
        editor = sharedPreferences.edit()
        editor.putInt("COUNT", count)
        editor.apply()
    }

    public fun getContNews() : Int {
        return sharedPreferences.getInt("COUNT", 0)
    }

}