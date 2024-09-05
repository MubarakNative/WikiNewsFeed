package com.mubarak.wikinews.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mubarak.wikinews.ui.WikiNewsApp
import com.mubarak.wikinews.ui.theme.WikiNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WikiNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WikiNewsApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}