package com.robertszekely.booklibrary.data.models

import com.google.gson.annotations.SerializedName

class Book(@SerializedName("_id") val id: String,
           val title: String,
           val author: String,
           val cover: String?)