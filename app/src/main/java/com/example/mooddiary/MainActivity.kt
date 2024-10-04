package com.example.mooddiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.ViewModelProvider
import com.example.mooddiary.nav.NavGraph
import com.example.mooddiary.ui.theme.MoodDiaryTheme
import com.example.mooddiary.viewModel.MoodViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoodViewModel::class.java)
        setContent {
            MoodDiaryTheme {  // Сохраняем тему
                val navController = rememberNavController()
                NavGraph(navController = navController, viewModel = viewModel) // Передаем ViewModel
            }
        }
    }
}