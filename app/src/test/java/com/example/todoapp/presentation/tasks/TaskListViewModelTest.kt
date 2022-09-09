package com.example.todoapp.presentation.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.todoapp.getOrAwaitValueTest
import com.example.todoapp.network.models.Tasks
import com.example.todoapp.network.models.TasksItem
import com.example.todoapp.repository.ITaskRepository
import com.example.todoapp.utils.Resource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class TaskListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var taskListViewModel: TaskListViewModel
    private lateinit var repository: ITaskRepository

    @Before
    fun setup() {
        repository = mock(ITaskRepository::class.java)
        taskListViewModel = TaskListViewModel(repository)
    }

    @Test
    fun `fetch getTask network call, return loading,success`() {
        val task = getTasks()
        Mockito.`when`(repository.getTasks()).thenAnswer {
            Single.just(task)
        }
        taskListViewModel.fetchGetTasks()
        val resultLoading =
            taskListViewModel._task.getOrAwaitValueTest(customValue = Resource.loading())
        assertNotNull(resultLoading)
        assertEquals(resultLoading, Resource.loading(null))
        val resultSuccess = taskListViewModel._task.getOrAwaitValueTest(
            customValue = Resource.success(
                task.todos
            )
        )
        assertNotNull(resultSuccess)
        assertEquals(resultSuccess, Resource.success(task.todos))
    }

    @Test
    fun `fetch getTask network call, return success`() {
        val task = getTasks()
        Mockito.`when`(repository.getTasks()).thenAnswer {
            Single.just(task)
        }
        taskListViewModel.fetchGetTasks()
        val result = taskListViewModel._task.getOrAwaitValueTest(customValue = Resource.success(task.todos))
        assertNotNull(result)
        assertEquals(result, Resource.success(task.todos))

    }

    @Test
    fun `fetch getTask network call, return Error`() {
        Mockito.`when`(repository.getTasks()).thenAnswer {
            Single.just(Exception())
        }
        taskListViewModel.fetchGetTasks()
        val result = taskListViewModel._task.getOrAwaitValueTest(customValue = Resource.error(""))
        assertNotNull(result)
        assertEquals(result, Resource.error("",null))

    }

    private fun getTasks(): Tasks {
        return Tasks(1, 1, listOf(TasksItem(true, 1, "XYZ", 1)), 30)
    }
}

