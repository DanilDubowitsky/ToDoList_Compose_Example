package com.example.todolist.ui.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseStateViewModel<STATE : Any, SIDE_EFFECT : Any> : ViewModel() {

    private val initialState by lazy { createInitialState() }
    abstract fun createInitialState(): STATE
    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    private val _sideEffectFlow: MutableSharedFlow<SIDE_EFFECT> = MutableSharedFlow()
    val sideEffectFlow: SharedFlow<SIDE_EFFECT> = _sideEffectFlow.asSharedFlow()

    fun updateState(update: STATE.() -> STATE) {
        _state.value = _state.value.update()
        _state.tryEmit(_state.value)
    }

    fun postSideEffect(sideEffect: SIDE_EFFECT) {
        _sideEffectFlow.tryEmit(sideEffect)
    }

    protected fun intent(action: CoroutineScope.() -> Unit) {
        viewModelScope.action()
    }
}
