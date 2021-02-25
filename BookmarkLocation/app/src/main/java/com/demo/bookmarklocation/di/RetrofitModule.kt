package com.demo.bookmarklocation.di

import com.demo.bookmarklocation.data.remote.WeatherService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttp)
    }

    @Singleton
    @Provides
    fun provideMovie(retrofitBuilder: Retrofit.Builder): WeatherService {
        return retrofitBuilder.build().create(WeatherService::class.java)
    }


    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttp: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        }
}