package com.example.demo1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo1.ui.theme.Demo1Theme

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactCampus(intent)
                }
            }
        }
    }
}

@Composable
fun ContactCampus(intent: Intent) {
    Box(modifier = Modifier.fillMaxSize()) {
        val name = intent.getStringExtra("NAME") ?: ""
        val registerNo = intent.getStringExtra("REGISTER_NO") ?: ""

        Image(
            painter = painterResource(id = R.drawable.third_wall),
            contentDescription = "Third Activity",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize().graphicsLayer(alpha = 0.9f)
        )

        Column(
//            modifier = Modifier.align(Alignment.Center)
            modifier = Modifier,
        ) {
            Text(text = "Name: $name", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "Register No: $registerNo", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }


        var loading by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )

                Button(
                    onClick = { loading = true },
                    enabled = !loading,
                    modifier = Modifier
                        .padding(top = 20.dp),

                    ) {
                    Text("Start loading")
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    val dummyIntent = Intent().apply {
        putExtra("NAME", "John Doe")
        putExtra("REGISTER_NO", "123456")
    }
    Demo1Theme {
        ContactCampus(dummyIntent)
    }
}