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
import com.example.mooddiary.ui.theme.CustomPurple500
import com.example.mooddiary.ui.theme.CustomTeal200
import com.example.mooddiary.ui.theme.CustomWhite
import com.example.mooddiary.viewModel.MoodViewModel
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
        }
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
                },
                label = { Text("Настроение") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = CustomTeal200,
                    unfocusedContainerColor = CustomWhite
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = note,
                onValueChange = {
                    note = it
                },
                label = { Text("Заметка") },
                modifier = Modifier.fillMaxHeight(0.5f),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = CustomTeal200,
                    unfocusedContainerColor = CustomWhite
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (mood.isBlank()) {
                        Toast.makeText(context, "Пожалуйста, заполните заголовок заметки", Toast.LENGTH_SHORT).show()
                    } else {
                        if (noteId == null) {
                            viewModel.addMoodNote(mood, note)
                            Toast.makeText(context, "Заметка добавлена", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.updateMoodNote(noteId, mood, note)
                            Toast.makeText(context, "Заметка обновлена", Toast.LENGTH_SHORT).show()
                        }
                        navController.navigate("home")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = CustomPurple500) // Измените здесь
            ) {
                Text(if (noteId == null) "Добавить Заметку" else "Обновить Заметку", color = CustomWhite)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("home") }, // Возвращаемся на экран со списком
                colors = ButtonDefaults.buttonColors(containerColor = CustomTeal200)
            ) {
                Text("Отменить", color = CustomWhite)
            }

        }
    }
}
