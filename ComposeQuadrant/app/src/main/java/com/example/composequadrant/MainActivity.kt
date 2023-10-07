package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val q1StrArr = stringArrayResource(R.array.quad1)
                    val quad1 = QuadrantSectionInfo(q1StrArr[0],q1StrArr[1], Color(q1StrArr[2].toLong(radix = 16)))
                    val q2StrArr = stringArrayResource(R.array.quad2)
                    val quad2 = QuadrantSectionInfo(q2StrArr[0],q2StrArr[1], Color(q2StrArr[2].toLong(radix = 16)))
                    val q3StrArr = stringArrayResource(R.array.quad3)
                    val quad3 = QuadrantSectionInfo(q3StrArr[0],q3StrArr[1], Color(q3StrArr[2].toLong(radix = 16)))
                    val q4StrArr = stringArrayResource(R.array.quad4)
                    val quad4 = QuadrantSectionInfo(q4StrArr[0],q4StrArr[1], Color(q4StrArr[2].toLong(radix = 16)))
                    val quadrantRowSectionList: List<List<QuadrantSectionInfo>> =
                        listOf(
                            listOf(quad1, quad2),
                            listOf(quad3, quad4)
                        )
                    Quadrant(quadrantRowSectionList)
                }
            }
        }
    }
}

data class QuadrantSectionInfo(val title: String, val body: String, val backgroundColor: Color)

@Composable
fun Quadrant(quadrantRowSectionInfoList: List<List<QuadrantSectionInfo>>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        content = {
            quadrantRowSectionInfoList.forEach{quadrantRowSectionInfo ->
                QuadrantRow(
                    quadrantSectionInfoList = quadrantRowSectionInfo,
                    modifier = modifier.weight(1f)
                )
            }
        }
    )
}

@Composable
fun QuadrantRow(quadrantSectionInfoList: List<QuadrantSectionInfo>, modifier: Modifier = Modifier){
    Row(
        modifier = modifier,
        content = {
            quadrantSectionInfoList.forEach{quadrantInfo ->
                QuadrantSection(
                    quadrantInfo,
                    modifier = modifier.weight(1f)
                )
            }
        }
    )
}

@Composable
fun QuadrantSection(quadrantSectionInfo: QuadrantSectionInfo, modifier: Modifier = Modifier){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(quadrantSectionInfo.backgroundColor)
            .padding(16.dp)
            .fillMaxSize()){
        Text(
            text = quadrantSectionInfo.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 16.dp)
        )
        Text(
            text = quadrantSectionInfo.body
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeQuadrantPreview() {

    ComposeQuadrantTheme {
        val q1StrArr = stringArrayResource(R.array.quad1)
        val quad1 = QuadrantSectionInfo(q1StrArr[0],q1StrArr[1], Color(q1StrArr[2].toLong(radix = 16)))
        val q2StrArr = stringArrayResource(R.array.quad2)
        val quad2 = QuadrantSectionInfo(q2StrArr[0],q2StrArr[1], Color(q2StrArr[2].toLong(radix = 16)))
        val q3StrArr = stringArrayResource(R.array.quad3)
        val quad3 = QuadrantSectionInfo(q3StrArr[0],q3StrArr[1], Color(q3StrArr[2].toLong(radix = 16)))
        val q4StrArr = stringArrayResource(R.array.quad4)
        val quad4 = QuadrantSectionInfo(q4StrArr[0],q4StrArr[1], Color(q4StrArr[2].toLong(radix = 16)))
        val quadrantRowSectionList: List<List<QuadrantSectionInfo>> =
                    listOf(
                        listOf(quad1, quad2),
                        listOf(quad3, quad4)
                    )
        Quadrant(quadrantRowSectionList)
    }
}