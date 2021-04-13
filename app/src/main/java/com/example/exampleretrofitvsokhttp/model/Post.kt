package com.example.exampleretrofitvsokhttp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("title")
    @Expose
    var title: String = ""

    @SerializedName("body")
    @Expose
    var body: String = ""

    @SerializedName("userId")
    @Expose
    var userId: Int = 0

    @SerializedName("id")
    @Expose
    var id: Int = 0

    override fun toString(): String {
        return "Title=${title},body=${body},userId=${userId},id=${id}"
    }
}
