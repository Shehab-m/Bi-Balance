package com.biBalance.myapplication.di

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.repository.BiBalanceRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideFootballRepository(repository: BiBalanceRepositoryImp): BiBalanceRepository
}