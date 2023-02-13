package com.example.todolist.ui.components.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.navigation.Navigator
import com.example.todolist.navigation.RouterCommand
import com.example.todolist.ui.components.creation.DayTaskCreationComponent
import com.example.todolist.ui.components.overview.TaskOverviewComponent
import com.example.todolist.ui.components.tasks.DayTasksComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Navigation(
    navigator: Navigator
) {
    val navController = rememberNavController()

    LaunchedEffect(key1 = navigator.commands) {
        navigator.commands.onEach { command ->
            when (command) {
                is RouterCommand.Exit -> navController.popBackStack()
                is RouterCommand.NavigateTo -> {
                    navController.navigate(command.screen.route)
                    Log.e("ROUTE", Navigator.Screen.TaskOverview.route)
                }
            }
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = navigator.startScreen.route
    ) {
        composable(route = Navigator.Screen.DayTasks.route) {
            DayTasksComponent()
        }
        composable(route = Navigator.Screen.TaskCreation.route) {
            DayTaskCreationComponent()
        }
        composable(
            route = Navigator.Screen.TaskOverview.route,
            arguments = listOf(navArgument(Navigator.Screen.TaskOverview.argumentId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            TaskOverviewComponent(
                taskId = backStackEntry.arguments?.getString(
                    Navigator.Screen.TaskOverview.argumentId
                )?.toLong() ?: 0L
            )
        }
    }
}
