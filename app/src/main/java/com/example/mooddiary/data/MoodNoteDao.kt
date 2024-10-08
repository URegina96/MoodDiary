package com.example.mooddiary.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mooddiary.models.MoodNote
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodNoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoodNote(note: MoodNote)

    @Update
    suspend fun updateMoodNote(note: MoodNote)

    @Query("SELECT * FROM moodnote ORDER BY date DESC")
    fun getAllMoodNotes(): Flow<List<MoodNote>>
}