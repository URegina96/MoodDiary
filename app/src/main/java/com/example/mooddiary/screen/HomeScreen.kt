package com.example.mooddiary.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mooddiary.MoodNote

@Composable
fun MoodNoteCard(note: MoodNote) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = "${note.mood}: ${note.note}")
    }
}
