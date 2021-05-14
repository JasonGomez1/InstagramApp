package com.example.instagramapp.data.repository

import com.example.instagramapp.data.local.UserDataStore
import com.example.instagramapp.data.remote.InstagramService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val service: InstagramService,
    private val dataStore: UserDataStore
) {

    suspend fun getUser() =
        service.getUser(
            checkNotNull(dataStore.accessToken),
            "id,username",
            checkNotNull(dataStore.userId)
        )
}
