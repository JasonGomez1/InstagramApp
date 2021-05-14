package com.example.instagramapp.data.remote

import com.example.instagramapp.data.remote.request.TokenRequest
import com.example.instagramapp.data.remote.response.MediaResponse
import com.example.instagramapp.data.remote.response.TokenResponse
import com.example.instagramapp.data.remote.response.UserResponse
import retrofit2.http.*

interface InstagramService {

    @POST(Endpoints.TOKEN)
    suspend fun getToken(@Body request: TokenRequest): TokenResponse

    @GET(Endpoints.USER_ID)
    suspend fun getUser(
        @Query("access_token") accessToken: String,
        @Query("fields") fields: String,
        @Path("user-id") userId: String
    ): UserResponse

    @GET(Endpoints.MEDIA_ID)
    suspend fun getMedia(
        @Query("access_token") accessToken: String,
        @Query("fields") fields: String,
        @Path("media-id") userId: String
    ): MediaResponse
}
