package com.practicum.vkeducation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.practicum.vkeducation.ui.theme.VkeducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkeducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val intent = Intent(this, SecondActivity::class.java)
                    EnterNumberScreen(
                        modifier = Modifier.padding(innerPadding),
                        onClickToSecondActivity = {
                            intent.apply {
                                putExtra("number", it)
                            }
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EnterNumberScreen(
    modifier: Modifier,
    onClickToSecondActivity: (String) -> Unit
) {
    val textFieldState = rememberTextFieldState()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            state = textFieldState,
            label = { Text(stringResource(R.string.text_field_label)) }
        )

        Button(
            onClick = { onClickToSecondActivity(textFieldState.text.toString()) },
            content = { Text(text = stringResource(R.string.button_open_second_activity))}
        )
    }
}
