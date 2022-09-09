package com.example.todoapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    private var timeMutableSharedFlow = MutableSharedFlow<Int>()
    val _timeSharedFlow = timeMutableSharedFlow.asSharedFlow()

    init {
        collectFlow()
    }

    val countdownflow = flow {
        val startValue = TIME_UNIT
        var currentValue = startValue
        while (currentValue > 0) {
            delay(DELAY_TIME_UNIT_IN_MS)
            currentValue--
            emit(currentValue)
        }
    }


    private fun collectFlow() {
        viewModelScope.launch {
            countdownflow.collect {
                timeMutableSharedFlow.emit(it)
            }
        }
    }

    companion object{
        private const val TIME_UNIT = 1
        private const val DELAY_TIME_UNIT_IN_MS = 1000L
    }
}
