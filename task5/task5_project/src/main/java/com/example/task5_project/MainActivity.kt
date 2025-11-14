package com.example.task5_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task5_project.ui.theme.Task5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CenteredImageWithButtons(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CenteredImageWithButtons(modifier: Modifier = Modifier) {

    val imageResources = listOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4,
        R.drawable.img5,
        R.drawable.img6
    )


    var currentImageIndex by remember { mutableStateOf(0) }

    // Функции для навигации
    fun nextImage() {
        if (currentImageIndex < imageResources.size - 1) {
            currentImageIndex++
        }
    }

    fun previousImage() {
        if (currentImageIndex > 0) {
            currentImageIndex--
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Текущее изображение
        Image(
            painter = painterResource(id = imageResources[currentImageIndex]),
            contentDescription = "Изображение ${currentImageIndex + 1}",
            modifier = Modifier.size(400.dp)
        )

        // Индикатор текущего изображения
        Text(
            text = "Изображение ${currentImageIndex + 1} из ${imageResources.size}",
            modifier = Modifier.padding(16.dp)
        )

        // Кнопки навигации
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = { previousImage() },
                enabled = currentImageIndex > 0 // Отключаем, если на первом изображении
            ) {
                Text("Назад")
            }

            Button(
                onClick = { nextImage() },
                enabled = currentImageIndex < imageResources.size - 1 // Отключаем, если на последнем изображении
            ) {
                Text("Вперед")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CenteredImageWithButtonsPreview() {
    Task5Theme {
        CenteredImageWithButtons()
    }
}