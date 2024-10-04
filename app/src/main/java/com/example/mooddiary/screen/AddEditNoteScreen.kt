package com.example.mooddiary.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mooddiary.viewModel.MoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(navController: NavHostController, noteId: Int?, viewModel: MoodViewModel = viewModel()) {
    var mood by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    val context = LocalContext.current // Получаем контекст для Toast

    if (noteId != null) {
        val existingNote = viewModel.moodNotes.collectAsState().value.find { it.id == noteId }
        existingNote?.let {
            mood = it.mood
            note = it.note
            Log.d("AddEditScreen", "Editing existing note: $it")
        } ?: Log.e("AddEditScreen", "Note not found for id: $noteId")
    } else {
        Log.d("AddEditScreen", "Adding new note")
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            TextField(
                value = mood,
                onValueChange = {
                    mood = it
                    Log.d("AddEditScreen", "Mood updated: $mood")
                },
                label = { Text("Настроение") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = note,
                onValueChange = {
                    note = it
                    Log.d("AddEditScreen", "Note updated: $note")
                },
                label = { Text("Заметка") },
                modifier = Modifier.fillMaxHeight(0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (noteId == null) {
                    viewModel.addMoodNote(mood, note)
                    Toast.makeText(context, "Заметка добавлена", Toast.LENGTH_SHORT).show()
                    Log.d("AddEditScreen", "Заметка добавлена: настроение=$mood, заметка=$note")
                } else {
                    viewModel.updateMoodNote(noteId, mood, note)
                    Toast.makeText(context, "Заметка обновлена", Toast.LENGTH_SHORT).show()
                    Log.d("AddEditScreen", "Заметка обновлена: id=$noteId, настроение=$mood, заметка=$note")
                }
                navController.navigate("home")
            }) {
                Text(if (noteId == null) "Добавить Заметку" else "Обновить Заметку")
            }
        }
    }
}
