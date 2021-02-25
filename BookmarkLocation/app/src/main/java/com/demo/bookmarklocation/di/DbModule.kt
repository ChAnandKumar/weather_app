package com.demo.bookmarklocation.di

import android.content.Context
import com.demo.bookmarklocation.data.local.LocationDao
import com.demo.bookmarklocation.data.local.LocationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext appContext: Context): LocationDatabase {
        return LocationDatabase.invoke(appContext)
    }

    @Provides
    fun provideMovieDao(movieDatabase: LocationDatabase): LocationDao {
        return movieDatabase.getMovieDao()
    }


}