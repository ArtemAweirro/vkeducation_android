package com.practicum.vkeducation

import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import com.practicum.vkeducation.ui.theme.VkeducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkeducationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val intent = Intent(this, SecondActivity::class.java)
                    val implicitIntent = Intent(ACTION_DIAL)
                    EnterNumberScreen(
                        modifier = Modifier.padding(innerPadding),
                        onClickToSecondActivity = {
                            intent.apply {
                                putExtra("number", it)
                            }
                            startActivity(intent)
                        },
                        onClickDial = {
                            implicitIntent.apply {
                                data = "tel:$it".toUri()
                            }
                            startActivity(implicitIntent)
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
    onClickToSecondActivity: (String) -> Unit,
    onClickDial: (String) -> Unit
) {
    val textFieldState = rememberTextFieldState()
    val context = LocalContext.current

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

        Button(
            onClick = {
                val numberFromField = textFieldState.text.toString()
                if (Patterns.PHONE.matcher(numberFromField).matches()) {
                    onClickDial(numberFromField)
                } else {
                    Toast.makeText(context, R.string.toast_check_number, Toast.LENGTH_SHORT).show()
                    return@Button
                }},
            content = { Text(text = stringResource(R.string.button_call_friend)) }
        )
    }
}
