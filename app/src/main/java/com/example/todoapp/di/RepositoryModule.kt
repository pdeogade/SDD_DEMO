package com.example.todoapp.di

import com.example.todoapp.network.TaskService
import com.example.todoapp.repository.ITaskRepository
import com.example.todoapp.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTaskRepository(taskService: TaskService): ITaskRepository {
        return TaskRepository(taskService = taskService)
    }
}

