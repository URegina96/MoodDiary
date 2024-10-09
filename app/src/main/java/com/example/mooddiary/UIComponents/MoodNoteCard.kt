package com.example.mooddiary.UIComponents

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mooddiary.models.MoodNote
import java.time.format.DateTimeFormatter

@Composable
fun MoodNoteCard(note: MoodNote, onClick: () -> Unit, onDelete: () -> Unit, backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically // Центрируем элементы по вертикали
        ) {
            Column(
                modifier = Modifier.weight(1f) // Займите оставшееся пространство для текста
            ) {
                Text(text = note.mood, style = MaterialTheme.typography.titleLarge)
                Text(text = note.note, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = {
                Log.d("MoodNoteCard", "Delete icon clicked for note: ${note.id}")
                onDelete()
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Note")
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
}
