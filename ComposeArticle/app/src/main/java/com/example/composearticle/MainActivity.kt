package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetComposeArticle(
                        stringResource(id = R.string.jetcompose_article_title),
                        stringResource(id = R.string.jetcompose_article_intro),
                        stringResource(id = R.string.jetcompose_article_body))
                }
            }
        }
    }
}

@Composable
fun JetComposeArticle(title: String, intro: String, body: String, modifier: Modifier = Modifier) {
    Column(
        content = {
            val image = painterResource(id = R.drawable.bg_compose_background)
            Image(
                painter = image,
                contentDescription = "JetCompose Article Header Image",
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = title,
                fontSize = 24.sp,
                modifier = modifier.padding(16.dp)
            )
            Text(
                text = intro,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(16.dp)
            )
            Text(
                text = body,
                textAlign = TextAlign.Justify,
                modifier = modifier.padding(16.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun JetComposeArticlePreview() {
    ComposeArticleTheme {
        JetComposeArticle(
            stringResource(id = R.string.jetcompose_article_title),
            stringResource(id = R.string.jetcompose_article_intro),
            stringResource(id = R.string.jetcompose_article_body))
    }
}