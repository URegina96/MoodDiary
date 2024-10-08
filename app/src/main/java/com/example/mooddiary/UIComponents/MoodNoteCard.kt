package com.example.mooddiary.UIComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mooddiary.models.MoodNote
import java.time.format.DateTimeFormatter

@Composable
fun MoodNoteCard(note: MoodNote, onClick: () -> Unit, backgroundColor: Color) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS        dd.MM.yyyy")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = note.mood)
            Text(text = note.note)
            Text(text = note.date.format(formatter))
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(com.example.mooddiary.ui.theme.backgroundColor) // Используйте переданный цвет
            .clickable(onClick = onClick)
    ) {
    }
}
