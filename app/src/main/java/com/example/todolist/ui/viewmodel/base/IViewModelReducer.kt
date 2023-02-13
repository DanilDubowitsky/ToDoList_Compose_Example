package com.example.todolist.ui.viewmodel.base

interface IViewModelReducer<VM_STATE: Any, UI_STATE: Any> {
    fun reduce(vmState: VM_STATE): UI_STATE
}
