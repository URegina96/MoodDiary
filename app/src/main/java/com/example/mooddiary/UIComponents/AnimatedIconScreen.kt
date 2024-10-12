package com.example.mooddiary.UIComponents

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mooddiary.R
import kotlin.random.Random

@Composable
fun AnimatedIconScreen() {
    // Инициализация анимаций для X и Y
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }

    // Размер и отступы иконки
    val iconSizeDp = 52.dp
    val paddingDp = 16.dp

    // Вычисляем рабочую область для анимации
    val workingAreaWidth = 500.dp // Размер контейнера 3x3 см
    val workingAreaHeight = 500.dp

    // Запуск анимации
    LaunchedEffect(Unit) {
        while (true) {
            val maxX = workingAreaWidth.value - iconSizeDp.value
            val maxY = workingAreaHeight.value - iconSizeDp.value

            // Генерируем случайные целевые позиции в рабочей области
            val targetX = Random.nextFloat() * maxX
            val targetY = Random.nextFloat() * maxY

            // Анимируем иконку к новым позициям
            offsetX.animateTo(targetX, animationSpec = tween(1000, easing = FastOutSlowInEasing))
            offsetY.animateTo(targetY, animationSpec = tween(1000, easing = FastOutSlowInEasing))
        }
    }

    Box(
        modifier = Modifier
            .size(workingAreaWidth, workingAreaHeight)
            .padding(paddingDp),
        contentAlignment = Alignment.Center
    ) {
        // Отображаем анимированную иконку
        Icon(
            painter = painterResource(id = R.drawable.ic_pencil),
            contentDescription = "Animated Pencil Icon",
            modifier = Modifier
                .size(iconSizeDp)
                .graphicsLayer {
                    translationX = offsetX.value
                    translationY = offsetY.value
                }
        )
    }
}
