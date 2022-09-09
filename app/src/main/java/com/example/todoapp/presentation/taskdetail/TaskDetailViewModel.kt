package com.example.todoapp.presentation.taskdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.network.models.TasksItem
import com.example.todoapp.repository.ITaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class TaskDetailViewModel @Inject constructor(private val taskRepository: ITaskRepository) : ViewModel() {

    private var _taskItem = MutableLiveData<TasksItem>()
    val taskItem: LiveData<TasksItem> = _taskItem

    private val compositeDisposable = CompositeDisposable()

    fun getTaskDetail(tasksItem: Int) {
        val disposable = taskRepository.getTaskDetail(tasksItem).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                  _taskItem.value = it
            }, {
                Log.d("","Something went wrong")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}