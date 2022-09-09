package com.example.todoapp.repository

import com.example.todoapp.network.TaskService
import com.example.todoapp.network.models.Tasks
import com.example.todoapp.network.models.TasksItem
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TaskRepositoryTest {
    private lateinit var taskService: TaskService
    private lateinit var taskRepository: TaskRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        taskService = mock(TaskService::class.java)
        taskRepository = TaskRepository(taskService)
    }

    @Test
    fun `test getTasks returns tasks from taskService`() {
        // Arrange
        val expectedTasks = getTasks()
        `when`(taskService.getTasks()).thenReturn(Single.just(expectedTasks))

        // Act
        val testObserver = taskRepository.getTasks().test()

        // Assert
        testObserver.assertNoErrors()
        testObserver.assertValue(expectedTasks)
    }

    @Test
    fun `test getTasks handles errors from taskService`() {
        // Arrange
        val error = Throwable("An error occurred")
        `when`(taskService.getTasks()).thenReturn(Single.error(error))

        // Act
        val testObserver = taskRepository.getTasks().test()

        // Assert
        testObserver.assertError(error)
    }

    @Test
    fun `test getTask returns task detail from taskService`() {
        // Arrange
        val expectedItem = getTaskItem()
        `when`(taskService.getTaskDetail(1)).thenReturn(Single.just(expectedItem))

        // Act
        val testObserver = taskRepository.getTaskDetail(1).test()

        // Assert
        testObserver.assertNoErrors()
        testObserver.assertValue(expectedItem)
    }

    private fun getTasks(): Tasks {
        return Tasks(1, 1, listOf(getTaskItem()), 30)
    }

    private fun getTaskItem(): TasksItem {
        return TasksItem(true, 1, "XYZ", 1)
    }
}