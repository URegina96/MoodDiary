package com.example.mooddiary.UIComponents
//представление заметки.
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mooddiary.models.MoodNote

@Composable
fun MoodNoteCard(note: MoodNote, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = note.mood)
            Text(text = note.note)
            Text(text = note.date.toString())
        }
    }
}