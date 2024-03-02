package com.example.communityfoodexchange

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.communityfoodexchange.ui.theme.CommunityFoodExchangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommunityFoodExchangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldExample()
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var showNameAndPhone by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var showNextButton by remember { mutableStateOf(true) }

    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            if (!showNameAndPhone) {
                FloatingActionButton(
                    onClick = {

                            // Only proceed if both name and phoneNumber fields are not empty
                            showNameAndPhone = true
                            // Show a toast message or handle the situation when name or phoneNumber is empty
//                            Toast.makeText(context, "Please fill in both name and phone number fields", Toast.LENGTH_SHORT).show()
//                        }
                    },
                    modifier = Modifier.width(330.dp),
                    content = {
                        Text("Next") // Change the text of the button
                    }
                )
            }
        },
        bottomBar = {
            if (showNameAndPhone) {
                BottomAppBar(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    content = {
                        FloatingActionButton(
                            onClick = {
                                if (name.isNotEmpty() && phoneNumber.isNotEmpty()) {
                                    val intent = Intent(context, Choose::class.java)

                                    intent.putExtra("name", name)

                                    context.startActivity(intent)
                                }else {
                                    Toast.makeText(context, "Please fill in both name and phone number fields", Toast.LENGTH_SHORT).show()
                                }


                            },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text("Next")
                        }
                    }
                )
            }
        }
    ) {
        innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                var text by rememberSaveable { mutableStateOf("") }
                if(showNameAndPhone){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            singleLine = true
                        )
                        TextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            label = { Text("Phone Number") },
                            singleLine = true
                        )
                    }
                }else {
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier
                            .size(width = 240.dp, height = 150.dp)
                    ) {
                        Text(
                            text = "COMMUNITY FOOD EXCHANGE",
                            modifier = Modifier
                                .padding(16.dp).fillMaxSize(),
                            textAlign = TextAlign.Center,
                            style = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp),
                        )
                    }
                }

            }
        }

    }
}


//@Composable
//fun WelcomePage(){
//    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = painterResource(id = R.drawable.base_pic),
//            contentDescription = "Demo Background",
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier
//                .matchParentSize()
//                .graphicsLayer(alpha = 0.9f)
//        )
//    }
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CommunityFoodExchangeTheme {
        ScaffoldExample()
    }
}