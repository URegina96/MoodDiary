package com.example.mooddiary.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mooddiary.UIComponents.MoodNoteCard
import com.example.mooddiary.ui.theme.CustomTeal700
import com.example.mooddiary.ui.theme.CustomWhite
import com.example.mooddiary.ui.theme.backgroundColor
import com.example.mooddiary.viewModel.MoodViewModel
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(navController: NavHostController, viewModel: MoodViewModel = viewModel()) {
    val moodNotes by viewModel.moodNotes.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add")
            }, containerColor = CustomTeal700) {
                Text("+", color = CustomWhite)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(moodNotes) { note ->
                    MoodNoteCard(note = note, onClick = {
                        navController.navigate("edit/${note.id}")
                    }, backgroundColor = backgroundColor,
                        onDelete = {
                            viewModel.deleteMoodNote(note)
                        })
                }
            }

            // Иконка для удаления всех заметок
            IconButton(
                onClick = { viewModel.deleteAllMoodNotes() },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp) // Немного отступа для лучшего визуального отображения
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Удалить все заметки")
            }
        }
    }
}
