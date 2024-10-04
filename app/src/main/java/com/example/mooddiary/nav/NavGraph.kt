package com.example.mooddiary.nav

import android.util.Log
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
    Log.d("NavGraph", "Navigating to home")
    NavHost(navController, startDestination = "home") {
        composable("home") {
            Log.d("NavGraph", "Navigating to HomeScreen")
            HomeScreen(navController)
        }
        composable("add") {
            Log.d("NavGraph", "Navigating to AddEditScreen")
            AddEditScreen(navController, null)
        }
        composable("edit/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toInt()
            Log.d("NavGraph", "Navigating to EditScreen with id: $noteId")
            AddEditScreen(navController, noteId)
        }
        composable("history") {
            Log.d("NavGraph", "Navigating to HistoryScreen")
            HistoryScreen()
        }
    }
}
