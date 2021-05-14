package com.example.instagramapp.di

import com.example.instagramapp.data.remote.Endpoints
import com.example.instagramapp.data.remote.InstagramService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @TokenRetrofit
    fun provideInstagramService(): InstagramService {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(Endpoints.TOKEN_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build().create(InstagramService::class.java)
    }

    @Provides
    @Singleton
    @ProfileRetrofit
    fun provideInstagramServiceProfile(): InstagramService {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(Endpoints.PROFILE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build().create(InstagramService::class.java)
    }
}
