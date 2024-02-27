package com.example.demo1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.demo1.ui.theme.Demo1Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Welcome to SecondActivity!", Toast.LENGTH_SHORT).show()
        setContent {
            Demo1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Courses()
                }
            }
        }
    }
}

val images = listOf(
    R.drawable.pic1,
    R.drawable.pic2,
    R.drawable.pic3,
    R.drawable.pic4,
    // Add more image resources as needed
)

@Composable
fun ImageItem(image: Int) {
    val painter: Painter = painterResource(id = image)
    Image(
        painter = painter,
        contentDescription = null, // Set content description as null if the image is purely decorative
        modifier = Modifier
            .size(390.dp, 390.dp)
            .padding(4.dp)
    )
}


@Composable
fun LocationButton(text: String, uri: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = text)
    }
}


@Composable
fun Courses() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.second_wall),
            contentDescription = "Demo Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize().graphicsLayer(alpha = 0.9f)
        )

        LazyRow(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(images.size) { index ->
                ImageItem(image = images[index])
            }
        }

        val context = LocalContext.current
        Button(
            onClick = {
                val intent = Intent(context, ThirdActivity::class.java).apply{
                    putExtra("NAME", "Rohit Tank")
                    putExtra("REGISTER_NO", "2347161")
                }
                context.startActivity(intent)
            },
            modifier = Modifier.align(Alignment.Center),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ){
            Text(text = "Send data", color=Color.White, fontWeight = FontWeight.Bold)
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            LocationButton("Central Campus", "geo:12.9344145,77.6010312?q=Central+Campus")
            LocationButton("Kengeri Campus", "geo:12.8638048,77.4324138?q=Kengeri+Campus")
            LocationButton("Lavasa Campus", "geo:18.4116868,73.5024362?q=Lavasa+Campus")
            LocationButton("Delhi NCR Campus", "geo:28.6832299,77.4073791?q=DelhiNCR+Campus")
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Demo1Theme {
        Courses()
    }
}