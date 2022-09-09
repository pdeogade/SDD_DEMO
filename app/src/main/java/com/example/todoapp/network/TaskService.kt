package com.example.todoapp.network

import com.example.todoapp.network.models.Tasks
import com.example.todoapp.network.models.TasksItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface TaskService {

    @GET("todos")
    fun getTasks(): Single<Tasks>

    @GET("todos/{id}")
    fun getTaskDetail(@Path("id") id: Int): Single<TasksItem>
}











