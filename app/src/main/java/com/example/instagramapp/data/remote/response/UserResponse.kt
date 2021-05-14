package com.example.instagramapp.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val id: String,
    val account_type: String,
    val media_count: String? = null,
    val username: String
)
