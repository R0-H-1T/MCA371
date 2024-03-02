package com.example.communityfoodexchange

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.communityfoodexchange.ui.theme.CommunityFoodExchangeTheme

class Choose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CommunityFoodExchangeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Choice(intent)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Choice(intent: Intent) {
    val radioOptions = listOf("Request/Order", "Donate", "Be part of community")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    val name = intent.getStringExtra("name") ?: ""
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome, $name!") },
            )
        } ,

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Add action for the floating action button here
                },
                modifier = Modifier
                    .width(330.dp),
                    //.align(Alignment.BottomEnd)  Align the button to the bottom end of the screen
                content = {
                    Text("Next") // Change the text of the button
                }
            )
        }

    ) {
        innerPadding ->
        Column(
            Modifier
                .selectableGroup()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = {
//                            onOptionSelected(text)
//
//                            val activityIntent = when( text ){
//                                "Request/Order" -> Intent(context, RequestOrder::class.java)
//                                "Donate" -> Intent(context, DonateCommunity::class.java)
//                                "Be part of community" -> Intent(context, BePartOfCom::class.java)
//                                else -> null
//                            }
//                            activityIntent?.let { intent -> startActivity(intent) }
                        } // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    val dummyIntent = Intent().apply {
        putExtra("NAME", "John Doe")
        putExtra("REGISTER_NO", "123456")
    }
    CommunityFoodExchangeTheme {
        Choice(dummyIntent)
    }
}