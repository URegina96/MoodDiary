package com.example.mooddiary.models
import java.time.LocalDateTime

data class MoodNote(
    val id: Int,
    val mood: String,
    val note: String,
    val date: LocalDateTime
)
