package com.example.mooddiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.ViewModelProvider
import com.example.mooddiary.data.MoodDatabase
import com.example.mooddiary.nav.NavGraph
import com.example.mooddiary.ui.theme.MoodDiaryTheme
import com.example.mooddiary.viewModel.MoodViewModel
import com.example.mooddiary.viewModel.MoodViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Получите экземпляр базы данных
        val database = MoodDatabase.getDatabase(application)

        // Используйте MoodViewModelFactory для создания ViewModel
        val viewModelFactory = MoodViewModelFactory(database)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MoodViewModel::class.java)

        setContent {
            MoodDiaryTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController, viewModel = viewModel) // Передаем ViewModel
            }
        }
    }
}
