package com.example.demo1

import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.demo1.ui.theme.Demo1Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.Role.Companion.Switch
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.sp
import java.time.LocalDate

import java.time.format.DateTimeFormatter // For formatting date

class Form : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {

//    var recordResponse by remember { mutableStateOf(false) }
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = painterResource(id = R.drawable.third_wall),
//            contentDescription = "Third Activity",
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.matchParentSize().graphicsLayer(alpha = 0.9f)
//        )
//        Column {
//            TopAppBar(
//                title = { Text(text = "Greetings App") }, // Title of the top bar
//                modifier = Modifier.fillMaxWidth(), // Make the top bar fill the width of the screen
//                backgroundColor = Color.White, // Background color of the top bar
//                contentColor = Color.White, // Text color of the top bar
//                elevation = AppBarDefaults.TopAppBarElevation // Elevation of the top bar
//            )
//            Spacer(modifier = Modifier.height(16.dp)) // Add some spacing below the top bar
//
//            Spacer(modifier = Modifier.height(16.dp)) // Add some spacing below the top bar
//
//            // Switch
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(horizontal = 16.dp)
//            ) {
//                Text(
//                    text = "Record your response",
//                    color = Color.White,
//                    modifier = Modifier.weight(1f)
//                )
//                Switch(
//                    checked = recordResponse,
//                    onCheckedChange = { recordResponse = it },
//
//                )
//
//            }
//
//            Spacer(modifier = Modifier.height(16.dp)) // Add some spacing below the switch
//
//        }
//    }



        var presses by remember { mutableStateOf(0) }
        var text by rememberSaveable { mutableStateOf("") }
        var checked by remember { mutableStateOf(true) }
//        var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Welcome to Christ")

                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "@christ2024. Times pressed - $presses",
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { presses++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                    text =
                    """
                    Enter your details below.
                """.trimIndent(),
                )


                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Email") },
                        placeholder = { Text("example@gmail.com") },
                        modifier = Modifier.padding(start = 15.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        }
                    )
                }

                val radioOptions = listOf("Male", "Female", "Others")
                val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

                Column(Modifier.selectableGroup()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth().padding(start = 8.dp, top = 20.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                        text = "Select gender - ",
                    )
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
                                .padding(horizontal = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == selectedOption),
                                onClick = null // null recommended for accessibility with screenreaders
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Title
                    Text(
                        text = "Qualification level",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )

                    // First row with labels and checkboxes
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // First label and checkbox
                        LabelWithCheckbox("Undergrad", checkedState = true)

                        // Spacer between labels and checkboxes
                        Spacer(modifier = Modifier.width(16.dp))

                        // Second label and checkbox
                        LabelWithCheckbox("Postgrad", checkedState = false)
                    }

                }

                Column {

                    Text(
                        text = "Courses available",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )

                    ListItem(
                        headlineContent = { Text("Master Of Computer Application") },
                        supportingContent = { Text("School of Science") },
                        trailingContent = { Text("MCA") },
                        leadingContent = {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Localized description",
                            )
                        }
                    )

                    ListItem(
                        headlineContent = { Text("Master of Computer Science in A.I.") },
                        supportingContent = { Text("School of Science") },
                        trailingContent = { Text("Msc AI ML") },
                        leadingContent = {
                            Icon(
                                Icons.Filled.Info,
                                contentDescription = "Localized description",
                            )
                        }
                    )


                }


            }
        }
}


@Composable
fun LabelWithCheckbox(label: String, checkedState: Boolean) {
    val (checked, onCheckedChange) = remember { mutableStateOf(checkedState) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = label,
//            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Demo1Theme {
        Greeting()
    }
}