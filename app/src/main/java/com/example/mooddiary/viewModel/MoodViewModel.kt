package com.example.mooddiary.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mooddiary.models.MoodNote
import java.time.LocalDateTime

class MoodViewModel : ViewModel() {
    private val _moodNotes = MutableStateFlow<List<MoodNote>>(
        // Инициализация тестовыми значениями
        listOf(
            MoodNote(1, "Счастлив", "Отличный день!", LocalDateTime.of(2023, 12, 25, 10, 30)),
            MoodNote(2, "Грустный", "Плохие новости.", LocalDateTime.of(2023, 12, 24, 16, 15))
        )
    )

    val moodNotes = _moodNotes.asStateFlow()

    fun addMoodNote(mood: String, note: String) {
        val newNote = MoodNote(
            id = (_moodNotes.value.size + 1),
            mood = mood,
            note = note,
            date = LocalDateTime.now() // Получаем текущее время и дату
        )
        _moodNotes.value = _moodNotes.value + newNote // Обновление списка через StateFlow
        Log.d("MoodViewModel", "Added new mood note: $newNote")
    }

    fun updateMoodNote(id: Int, mood: String, note: String) {
        val existingNotes = _moodNotes.value.toMutableList()
        val existingNote = existingNotes.find { it.id == id }
        if (existingNote != null) {
            val updatedNote = existingNote.copy(mood = mood, note = note, date = LocalDateTime.now()) // Обновляем дату при редактировании
            existingNotes[existingNotes.indexOf(existingNote)] = updatedNote
            _moodNotes.value = existingNotes // Обновление списка через StateFlow
            Log.d("MoodViewModel", "Updated mood note: $updatedNote")
        } else {
            Log.e("MoodViewModel", "Failed to update mood note, ID not found: $id")
        }
    }
}
