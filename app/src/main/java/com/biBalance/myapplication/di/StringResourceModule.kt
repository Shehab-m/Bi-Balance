package com.biBalance.myapplication.di

import android.app.Application
import android.content.Context
import com.biBalance.myapplication.util.StringDictionary
import com.biBalance.myapplication.util.StringResources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StringResourceModule {
    @Singleton
    @Provides
    fun provideStringResource(@ApplicationContext context: Context): StringDictionary {
        return StringResources(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ContextModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}