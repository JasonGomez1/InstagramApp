package com.example.instagramapp.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(val access_token: String, val user_id: Int)
