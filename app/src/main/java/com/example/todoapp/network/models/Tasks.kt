package com.example.todoapp.network.models

import com.google.gson.annotations.SerializedName

data class Tasks(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("todos")
    val todos: List<TasksItem>,
    @SerializedName("total")
    val total: Int
)