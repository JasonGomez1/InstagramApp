package com.example.instagramapp.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MediaResponse(
    val id: String,
    val caption: String? = null,
    val permalink: String? = null,
    val thumbnail_url: String? = null,
    val media_type: String,
    val media_url: String,
    val username: String,
    val timestamp: String
)
