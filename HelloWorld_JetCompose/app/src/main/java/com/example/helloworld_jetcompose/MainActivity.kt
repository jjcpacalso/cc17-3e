package com.example.helloworld_jetcompose

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld_jetcompose.ui.theme.HelloWorld_JetComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorld_JetComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HappyBirthdayGreeting("Happy Birthday!", "From Jerry", onClick = {Toast.makeText(this, "Testing context", Toast.LENGTH_SHORT).show()})
                }
            }
        }
    }
}

@Composable
fun HappyBirthdayGreeting(message: String, signature: String, modifier: Modifier = Modifier, onClick: () -> Unit){
    Image(painterResource(id = R.drawable.basic_usage), contentDescription = "Image")
    Column(verticalArrangement = Arrangement.Center){
        Text(text = message, fontSize=100.sp, lineHeight=116.sp, textAlign= TextAlign.Center)
        Text(text = signature, fontSize=36.sp, modifier= Modifier
            .padding(16.dp)
            .align(Alignment.End))
        Button(onClick = { onClick() },
                modifier.align(Alignment.CenterHorizontally)) {
            Text(text="Test Button")
        }
    }
    Row{
        Text(text = message, fontSize=100.sp, lineHeight=116.sp, textAlign= TextAlign.Center)
        Text(text = signature, fontSize=36.sp, modifier= Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorld_JetComposeTheme {
        HappyBirthdayGreeting(message = "Happy Birthday!", signature = "From Jerry", onClick = { Log.d("TEST", "Test")})
    }
}