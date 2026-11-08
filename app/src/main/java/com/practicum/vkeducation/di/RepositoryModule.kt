package com.practicum.vkeducation.di

import com.practicum.vkeducation.data.repository.AppListRepositoryImpl
import com.practicum.vkeducation.domain.repository.AppListRepository
import com.practicum.vkeducation.domain.usecase.GetAppListUseCase
import com.practicum.vkeducation.domain.usecase.impl.GetAppListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppListRepository(
        impl: AppListRepositoryImpl
    ): AppListRepository

    @Binds
    abstract fun bindGetAppListUseCase(
        impl: GetAppListUseCaseImpl
    ): GetAppListUseCase
}