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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo1.ui.theme.Demo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun DemoScreen(){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.iphone1),
            contentDescription = "Demo Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize().graphicsLayer(alpha = 0.9f)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.christ),
                contentDescription = "Christ Logo",
                modifier = Modifier.size(350.dp), // Adjust size as needed
            )

            Text(text = "Welcome", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)

            val showAlertMessage = remember{mutableStateOf(false)}
            if ( showAlertMessage.value){
                AlertDialog(
                    onDismissRequest = {showAlertMessage.value = false},
                    title = {Text(text = "WARNING")},
                    text = {Text(
                        text = "Hello User! To navigate to the next page, tap on NEXT button. Thank you."
                    )},
                    confirmButton = {
                        val context = LocalContext.current
                        Button(
                            onClick = {
                                showAlertMessage.value = false
                                val intent = Intent(context, SecondActivity::class.java)
                                context.startActivity(intent)
                            },
                            colors = ButtonDefaults.buttonColors(Color.Black)
                        ){
                            Text(text = "NEXT", color=Color.White, fontWeight = FontWeight.Bold)
                        }
                    }

                )
            }

            Button(
                onClick = { showAlertMessage.value = true },
                modifier = Modifier.padding(6.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ){
                Text(text = "Click here to proceed", fontSize = 18.sp, color=Color.White)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Demo1Theme {
        DemoScreen()
    }
}