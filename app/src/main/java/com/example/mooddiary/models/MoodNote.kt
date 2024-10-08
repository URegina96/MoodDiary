package com.example.mooddiary.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "moodnote")
data class MoodNote(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val mood: String,
    val note: String,
    val date: LocalDateTime
)
