package com.axproject.newsapplication.ui.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    @Expose
    private var id: String,

    @SerializedName("name")
    @Expose
    private var name: String

)