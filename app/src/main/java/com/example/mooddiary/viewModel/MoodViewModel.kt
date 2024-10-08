package com.example.mooddiary.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mooddiary.data.MoodDatabase
import com.example.mooddiary.models.MoodNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
class MoodViewModel(private val database: MoodDatabase) : ViewModel() {
    private val _moodNotes = MutableStateFlow<List<MoodNote>>(emptyList())
    val moodNotes = _moodNotes.asStateFlow()

    init {
        loadInitialData()
        getMoodNotes()
    }

    private fun loadInitialData() {
        // Подгружаем тестовые данные
        val initialNotes = listOf(
            MoodNote(1, "Счастлив", "Отличный день!", LocalDateTime.of(2023, 12, 25, 10, 30)),
            MoodNote(2, "Грустный", "Плохие новости.", LocalDateTime.of(2023, 12, 24, 16, 15))
        )
        // Объединяем тестовые данные с пустым списком, чтобы сохранить структуру
        _moodNotes.value = initialNotes
        Log.d("MoodViewModel", "Loaded initial mood notes: $initialNotes")
    }
    /*
    private fun loadInitialData() {
    // Подгружаем тестовые данные
    val initialNotes = listOf(
        MoodNote(1, "Счастлив", "Отличный день!", LocalDateTime.of(2023, 12, 25, 10, 30)),
        MoodNote(2, "Грустный", "Плохие новости.", LocalDateTime.of(2023, 12, 24, 16, 15))
    )

    // Вставляем тестовые данные в базу данных, если это необходимо
    initialNotes.forEach { note ->
        database.moodNoteDao().insertMoodNote(note)
    }

    // Теперь загружаем заметки из базы данных
    getMoodNotes()
}
     */

    private fun getMoodNotes() {
        viewModelScope.launch {
            database.moodNoteDao().getAllMoodNotes().collect { notes ->
                // Объединяем заметки из базы данных с тестовыми заметками
                val combinedNotes = (notes + _moodNotes.value).distinctBy { it.id }
                // Сортируем заметки по дате (новые сверху)
                _moodNotes.value = combinedNotes.sortedByDescending { it.date }
            }
        }
    }
/*
пример, как можно обновить метод getMoodNotes, чтобы учесть и тестовые заметки
private fun getMoodNotes() {
    viewModelScope.launch {
        database.moodNoteDao().getAllMoodNotes().collect { notesFromDb ->
            // Сохраняем предыдущие тестовые заметки
            val currentNotes = _moodNotes.value

            // Объединяем только те заметки, которые отсутствуют в базе данных
            val combinedNotes = (notesFromDb + currentNotes.filter { note ->
                notesFromDb.none { dbNote -> dbNote.id == note.id }
            }).distinctBy { it.id }

            // Сортируем заметки по дате (новые сверху)
            _moodNotes.value = combinedNotes.sortedByDescending { it.date }
        }
    }
}
*/

    fun addMoodNote(mood: String, note: String) {
        val newNote = MoodNote(
            id = 0, // 0 для автоматической генерации ID
            mood = mood,
            note = note,
            date = LocalDateTime.now()
        )
        viewModelScope.launch {
            database.moodNoteDao().insertMoodNote(newNote)
            Log.d("MoodViewModel", "Added new mood note: $newNote")

            // Обновляем локальное состояние после добавления, добавляем новое к текущим
            getMoodNotes() // Перезагружаем все заметки
        }
    }

    fun updateMoodNote(id: Int, mood: String, note: String) {
        val updatedNote = MoodNote(
            id = id,
            mood = mood,
            note = note,
            date = LocalDateTime.now()
        )
        viewModelScope.launch {
            database.moodNoteDao().updateMoodNote(updatedNote)
            Log.d("MoodViewModel", "Updated mood note: $updatedNote")

            // Обновляем локальное состояние после изменения
            getMoodNotes() // Перезагружаем все заметки
        }
    }
}
