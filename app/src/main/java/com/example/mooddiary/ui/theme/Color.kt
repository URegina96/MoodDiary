package com.example.mooddiary.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val CustomPurple200 = Color(0xFFA6C9F2)
val CustomPurple500 = Color(0xFF3C6CA8)
val CustomTeal200 = Color(0xFF4490DC)
val CustomTeal700 = Color(0xFF2264A6)
val CustomBlack = Color(0xFF739FD9)
val CustomWhite = Color(0xFFFFFFFF)
val backgroundColor = Color(0xFFC8E6C9)

val DarkColorScheme = darkColorScheme(
    primary = CustomPurple500,
    secondary = CustomTeal700,
    tertiary = CustomTeal200
)

val LightColorScheme = lightColorScheme(
    primary = CustomPurple200,
    secondary = CustomTeal200,
    tertiary = CustomTeal700
)
