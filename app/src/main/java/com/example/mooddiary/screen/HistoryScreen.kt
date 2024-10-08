package com.example.mooddiary.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mooddiary.UIComponents.MoodNoteCard
import com.example.mooddiary.ui.theme.CustomPurple200
import com.example.mooddiary.viewModel.MoodViewModel


@Composable
fun HistoryScreen(viewModel: MoodViewModel = viewModel()) {
    val moodNotes by viewModel.moodNotes.collectAsState()

    Scaffold { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            items(moodNotes) { note ->
                MoodNoteCard(note = note, onClick = {

                }, backgroundColor = CustomPurple200)
            }
        }
    }
}
