package com.example.mooddiary.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.mooddiary.models.MoodNote
import androidx.room.TypeConverters
@Database(entities = [MoodNote::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MoodDatabase : RoomDatabase() {

    abstract fun moodNoteDao(): MoodNoteDao

    companion object {
        @Volatile
        private var INSTANCE: MoodDatabase? = null

        fun getDatabase(context: Context): MoodDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoodDatabase::class.java,
                    "mood_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
