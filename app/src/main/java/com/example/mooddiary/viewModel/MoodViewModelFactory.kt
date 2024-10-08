package com.example.mooddiary.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mooddiary.data.MoodDatabase

class MoodViewModelFactory(private val database: MoodDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoodViewModel::class.java)) {
            return MoodViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}