package com.example.mooddiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.mooddiary.nav.NavGraph
import com.example.mooddiary.ui.theme.MoodDiaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoodDiaryTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}