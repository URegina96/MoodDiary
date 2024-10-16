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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mooddiary.models.MoodNote
import java.time.format.DateTimeFormatter
import com.example.mooddiary.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.Image // Импортируйте Image вместо Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
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
                Text(
                    text = note.mood,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1, // Ограничиваем до 1 строки
                    overflow = TextOverflow.Ellipsis // Добавляем многоточие
                )
                Text(
                    text = note.note,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2, // Ограничиваем до 2 строк
                    overflow = TextOverflow.Ellipsis // Добавляем многоточие
                )
            }
            IconButton(onClick = {
                Log.d("MoodNoteCard", "Delete icon clicked for note: ${note.id}")
                onDelete()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = "Удалить заметку",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor) // Используйте переданный цвет
                .clickable(onClick = onClick)
        ) {
        }
    }
}
