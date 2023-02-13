package com.example.todolist.navigation

sealed interface RouterCommand {

    data class NavigateTo(
        val screen: Navigator.Screen
    ) : RouterCommand

    object Exit : RouterCommand
    // TODO: add more support commands
}