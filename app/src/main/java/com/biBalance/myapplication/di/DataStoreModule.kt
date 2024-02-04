package com.biBalance.myapplication.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.biBalance.myapplication.data.source.local.AuthPreferences
import com.biBalance.myapplication.data.source.local.AuthPreferencesImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideUserDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            applicationContext.preferencesDataStoreFile("com.cheesecake.kickoff.onboarding_prefs")
        }
    }

    @Singleton
    @Provides
    fun provideAuthDataStorePreferences(dataStore: DataStore<Preferences>): AuthPreferences {
        return AuthPreferencesImp(dataStore)
    }
}
