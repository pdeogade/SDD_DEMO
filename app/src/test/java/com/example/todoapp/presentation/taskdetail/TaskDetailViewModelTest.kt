package com.example.todoapp.presentation.taskdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.constraintlayout.motion.utils.ViewState
import com.example.todoapp.network.models.TasksItem
import com.example.todoapp.repository.ITaskRepository
import com.example.todoapp.getOrAwaitValueTest
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response

@ExperimentalCoroutinesApi
class TaskDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val taskRepository: ITaskRepository = mock(ITaskRepository::class.java)
    private val viewModel = TaskDetailViewModel(taskRepository)
    private lateinit var testScheduler: TestScheduler


    @Before
    fun setup(){
        testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }
    }

    @Test
    fun testFetchGetTasksSuccess() {
        // Arrange
        val tasksItem = getTaskItem()

        `when`(taskRepository.getTaskDetail(1)).thenReturn(Single.just(tasksItem))

        // Act
        viewModel.getTaskDetail(1)

        //Assert
        viewModel.taskItem.observeForever{
            assertEquals(tasksItem, it)
        }
    }


    @Test
    fun testFetchGetTasksSuccess1() {
        // Arrange
        val tasksItem = getTaskItem()

        `when`(taskRepository.getTaskDetail(1)).thenReturn(Single.just(tasksItem))

        // Act
        viewModel.getTaskDetail(1)
        testScheduler.triggerActions()

        //Assert
        viewModel.taskItem.observeForever{
            assertEquals(tasksItem, it)
        }
    }

    private fun getTaskItem(): TasksItem {
        return TasksItem(true, 1, "XYZ", 1)
    }
}

