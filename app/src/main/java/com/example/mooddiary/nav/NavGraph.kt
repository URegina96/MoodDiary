package com.example.mooddiary.nav
//файл для настройки навигации между экранами.
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mooddiary.screen.AddEditScreen
import com.example.mooddiary.screen.HomeScreen
import com.example.mooddiary.screen.HistoryScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("add") { AddEditScreen(navController, null) }
        composable("edit/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toInt()
            AddEditScreen(navController, noteId)
        }
        composable("history") { HistoryScreen() }
    }
}