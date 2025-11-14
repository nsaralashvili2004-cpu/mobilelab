package com.example.task5_tap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task5_tap.ui.theme.Task5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Task5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentState by remember { mutableStateOf(LemonState.TREE) }
    var clickCount by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFFE3F2FD),
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    width = 3.dp,
                    color = Color(0xFF1976D2),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(12.dp)

                .clickable {
                    clickCount++
                    val clicksNeeded = when (currentState) {
                        LemonState.TREE -> 2      // 2 клика чтобы собрать с дерева
                        LemonState.PICKING -> 3   // 3 клика чтобы очистить
                        LemonState.LEMON -> 1     // 1 клик чтобы выжать сок
                        LemonState.JUICE -> 4     // 4 клика чтобы начать новый цикл
                    }
                    if (clickCount >= clicksNeeded) {
                        currentState = when (currentState) {
                            LemonState.TREE -> LemonState.PICKING
                            LemonState.PICKING -> LemonState.LEMON
                            LemonState.LEMON -> LemonState.JUICE
                            LemonState.JUICE -> LemonState.TREE
                        }
                        clickCount = 0
                    }
                }
        ) {
            Image(
                painter = painterResource(currentState.imageRes),
                contentDescription = currentState.description,
                modifier = Modifier
                    .height(350.dp)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = currentState.text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Нажми на изображение",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

// Перечисление состояний лимона
enum class LemonState(
    val imageRes: Int,
    val text: String,
    val description: String
) {
    TREE(
        imageRes = R.drawable.lemon_tree,
        text = "Дерево с лимонами",
        description = "Лимонное дерево с плодами"
    ),
    PICKING(
        imageRes = R.drawable.lemon_squeeze,
        text = "Собираем лимон...",
        description = "Сбор лимона с дерева"
    ),
    LEMON(
        imageRes = R.drawable.lemon_drink,
        text = "Свежий лимон",
        description = "Собранный лимон"
    ),
    JUICE(
        imageRes = R.drawable.lemon_restart,
        text = "Вкусный лимонад!",
        description = "Стакан лимонада"
    )
}

@Preview(showBackground = true)
@Composable
fun LemonAppPreview() {
    Task5Theme {
        LemonApp()
    }
}