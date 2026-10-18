package com.practicum.vkeducation.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.practicum.vkeducation.navigation.RootNavHost
import com.practicum.vkeducation.presentation.theme.VkeducationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkeducationTheme {
                RootNavHost()
            }
        }
    }
}