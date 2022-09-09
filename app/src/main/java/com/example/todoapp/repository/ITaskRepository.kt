package com.example.todoapp.repository

import com.example.todoapp.network.models.Tasks
import com.example.todoapp.network.models.TasksItem
import io.reactivex.rxjava3.core.Single
interface ITaskRepository {

    fun getTasks(): Single<Tasks>

    fun getTaskDetail(tasksItem: Int): Single<TasksItem>
}
