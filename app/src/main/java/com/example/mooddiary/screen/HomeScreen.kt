package com.example.mooddiary.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mooddiary.R
@Composable
fun HomeScreen(navController: NavHostController, viewModel: MoodViewModel = viewModel()) {
    val moodNotes by viewModel.moodNotes.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add")
            }, containerColor = CustomTeal700) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Добавить заметку",
                    modifier = Modifier.size(24.dp)
                )
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

            // Иконка для удаления всех заметок с фоном
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .size(56.dp) // Размеры для кнопки
                    .background(CustomTeal700, shape = MaterialTheme.shapes.small) // Фоновый цвет
                    .clickable { viewModel.deleteAllMoodNotes() } // Добавляем обработчик клика
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = "Удалить все заметки",
                    modifier = Modifier.size(24.dp).align(Alignment.Center) // Центрируем иконку внутри Box
                )
            }
        }
    }
}
