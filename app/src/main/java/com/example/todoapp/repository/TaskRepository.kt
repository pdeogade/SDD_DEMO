package com.example.todoapp.repository

import com.example.todoapp.network.TaskService
import com.example.todoapp.network.models.Tasks
import com.example.todoapp.network.models.TasksItem
import io.reactivex.rxjava3.core.Single

class TaskRepository(
    private val taskService: TaskService,
) : ITaskRepository {
    override fun getTasks(): Single<Tasks> {
       return taskService.getTasks()
    }

    override fun getTaskDetail(taskId: Int): Single<TasksItem> {
        return taskService.getTaskDetail(taskId)
    }
}