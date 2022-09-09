package com.example.todoapp.presentation.tasks


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.network.models.TasksItem
import com.example.todoapp.repository.ITaskRepository
import com.example.todoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

import javax.inject.Inject



@HiltViewModel
class TaskListViewModel @Inject constructor(private val taskRepository: ITaskRepository) :
    ViewModel() {

    private var task = MutableLiveData<Resource<List<TasksItem>>>()
    val _task: LiveData<Resource<List<TasksItem>>> = task

    private var _onDetailScreenClicked  = MutableLiveData<Int>()
    val onDetailScreenClicked: LiveData<Int> = _onDetailScreenClicked

    private val compositeDisposable = CompositeDisposable()

    fun fetchGetTasks() {
        task.postValue(Resource.loading())
        val disposable = taskRepository.getTasks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                task.value = Resource.success(response.todos)
            }, { _ ->
                task.value = Resource.error("An error occurred}")
            })

        compositeDisposable.add(disposable)
    }

    fun onTaskListItemClicked(tasksItem: TasksItem){
        _onDetailScreenClicked.value = tasksItem.id
    }

    fun onTaskDetailNavigationComplete() {
        _onDetailScreenClicked.value = null
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}