package com.example.instagramapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val CLIENT_ID = "505109177320287"
const val REDIRECT_URI = "https://socialsizzle.herokuapp.com/auth/"
const val CLIENT_SECRET = "d21a405a8f41b679d898c965202edd31"

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
