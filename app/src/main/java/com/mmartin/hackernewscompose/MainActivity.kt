package com.mmartin.hackernewscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mmartin.hackernewscompose.models.User
import com.mmartin.hackernewscompose.ui.theme.HackerNewsComposeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val user = User(
      id = "Test User"
    )
    setContent {
      HackerNewsComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Greeting(user)
        }
      }
    }
  }
}

@Composable
fun Greeting(user: User, modifier: Modifier = Modifier) {
  Text(
    text = "Hello ${user.id}!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  HackerNewsComposeTheme {
    Greeting(User("Dummy User"))
  }
}