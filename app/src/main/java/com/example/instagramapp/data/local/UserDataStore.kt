package com.example.instagramapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.instagramapp.utils.userDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

// TODO consider injecting dispatcher instead

@Singleton
class UserDataStore @Inject constructor(@ApplicationContext val context: Context) {
    private val scope = MainScope()
    private var preferences: Preferences? = null

    init {
        scope.launch {
            context.userDataStore.data.collect {
                preferences = it
            }
        }
    }

    var accessToken: String?
        set(value) {
            scope.launch {
                context.userDataStore.edit { prefs ->
                    value?.let {
                        prefs[ACCESS_TOKEN_KEY] = value
                    }
                }
            }
        }
        get() = preferences?.get(ACCESS_TOKEN_KEY)

    var userId: String?
        set(value) {
            scope.launch {
                context.userDataStore.edit { prefs ->
                    value?.let {
                        prefs[USER_ID_KEY] = value
                    }
                }
            }
        }
        get() = preferences?.get(USER_ID_KEY)

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token_key")
        private val USER_ID_KEY = stringPreferencesKey("user_id_key")
    }
}
