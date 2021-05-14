package com.example.instagramapp.data.remote.request

import com.example.instagramapp.utils.CLIENT_ID
import com.example.instagramapp.utils.CLIENT_SECRET
import com.example.instagramapp.utils.REDIRECT_URI
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenRequest(
    val client_id: String = CLIENT_ID,
    val client_secret: String = CLIENT_SECRET,
    val code: String,
    val grant_type: String = "authorization_code",
    val redirect_uri: String = REDIRECT_URI
)
