package com.example.task5_calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task5_calc.ui.theme.Task5Theme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task5Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") }
    var percentInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) } // Состояние для ползунка округления
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val present =  percentInput.toDoubleOrNull() ?: 15.0;
   // val tip = calculateTip(amount,present)

    val tip = calculateTip(amount, present, roundUp) // состояние округления
    Column(
        modifier = Modifier
            .padding(60.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )  {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier.padding(bottom = 16.dp)
        )
//        Text(
//            text = stringResource(R.string.tip_amount, "$0.00"),
//            style = MaterialTheme.typography.displaySmall
//        )
        TextField(
            value = amountInput,
            onValueChange = { newText ->
                amountInput = newText // обновляем состояние при вводе
            },
            label = { Text("Сумма счета") }, // Подпись поля
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, // Цифровая клавиатура
            ),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        TextField(
            value = percentInput, // Текущее значение
            onValueChange = { newText ->
                percentInput = newText // Обновляем состояние при вводе
            },
            label = { Text("процент скидки") }, // Подпись поля
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, //цифровая клавитару
            ),

            modifier = Modifier.padding(bottom = 32.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Округлить до целого числа",
                style = MaterialTheme.typography.bodyMedium
            )
            Switch(
                checked = roundUp,
                onCheckedChange = { newState ->
                    roundUp = newState
                }
            )

        }
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )

//        Text(
//            text = stringResource(R.string.tip_amount, tip),
//            style = MaterialTheme.typography.displaySmall
//        )
    }
    }

private fun calculateTip(amount: Double, percent: Double, roundUp: Boolean = false): String {
    var tip = percent / 100 * amount
    if (amount <0 || percent<0){
        return "Введите положительное число"
    }


    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }

    return NumberFormat.getCurrencyInstance().format(tip)
}


@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    Task5Theme {
        TipTimeLayout()
    }
}