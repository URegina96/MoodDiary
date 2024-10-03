package com.example.mooddiary.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mooddiary.UIComponents.MoodNoteCard
import com.example.mooddiary.viewModel.MoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: MoodViewModel = viewModel()) {
    // Исправлено: теперь moodNotes - StateFlow
    val moodNotes by viewModel.moodNotes.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add") }) {
                Text("+")
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            items(moodNotes) { note ->
                MoodNoteCard(note = note, onClick = {
                    navController.navigate("edit/${note.id}") // Переход к редактированию заметки
                })
            }
        }
    }
}
