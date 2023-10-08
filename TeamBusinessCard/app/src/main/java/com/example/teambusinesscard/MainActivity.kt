package com.example.teambusinesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.teambusinesscard.ui.theme.TeamBusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamBusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val logoWithNameInfo = LogoWithNameInfo(R.drawable.android_logo, "Android Logo", "Jerry Junior C. Pacalso", "Team JJCP")
                    val contactInfoList: List<ContactInfo> = listOf(
                        ContactInfo(R.drawable.baseline_phone_24, "Phone Icon", "+091919191919"),
                        ContactInfo(R.drawable.baseline_share_24, "Share Icon", "@JerryJuniorPacalso"),
                        ContactInfo(R.drawable.baseline_email_24, "E-mail Icon", "jjcpacalso@uc-bcf.edu.ph")
                    )
                    TeamBusinessCard(logoWithNameInfo, contactInfoList)
                }
            }
        }
    }
}

data class LogoWithNameInfo(val logoResourceId: Int, val logoDescription: String, val fullName: String, val teamName: String)

@Composable
fun TeamBusinessCard(logoWithNameInfo: LogoWithNameInfo, contactInfoList: List<ContactInfo>, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF3ddc84))
    ) {
        Column (
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier.weight(1f)
        ){
            LogoWithName(
                logoWithNameInfo,
            )
        }
        Column (
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier
                .weight(1f)
                .padding(50.dp)
        ){
            Contacts(
                contactInfoList,
                modifier
            )
        }
    }
}

@Composable
fun LogoWithName(logoWithNameInfo: LogoWithNameInfo, modifier: Modifier = Modifier){
    val image = painterResource(id = logoWithNameInfo.logoResourceId)
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ){
        Image(
            painter = image,
            contentDescription = logoWithNameInfo.logoDescription,
            modifier = modifier
                .background(Color.Black)
                .height(100.dp)
                .width(100.dp)
        )
        Text(
            text = logoWithNameInfo.fullName,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
        Text(
            text = logoWithNameInfo.teamName,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
    }
}

data class ContactInfo(val iconResourceId: Int, val iconDescription: String, val text: String)

@Composable
fun Contacts(contactInfoList: List<ContactInfo>, modifier: Modifier = Modifier){
    Column (
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ){
        contactInfoList.forEach{contactInfo ->
            Row (
                modifier = modifier
            ){
                Icon(
                    painter = painterResource(id = contactInfo.iconResourceId),
                    contentDescription = contactInfo.iconDescription,
                    modifier = modifier
                        .height(20.dp)
                        .width(20.dp)
                )
                Text(
                    fontSize = 13.sp,
                    text = contactInfo.text,
                    modifier = modifier
                        .padding(10.dp,0.dp,0.dp,0.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TeamBusinessCardTheme {
        val logoWithNameInfo = LogoWithNameInfo(R.drawable.android_logo, "Android Logo", "Jerry Junior C. Pacalso", "Team JJCP")
        val contactInfoList: List<ContactInfo> = listOf(
            ContactInfo(R.drawable.baseline_phone_24, "Phone Icon", "+091919191919"),
            ContactInfo(R.drawable.baseline_share_24, "Share Icon", "@JerryJuniorPacalso"),
            ContactInfo(R.drawable.baseline_email_24, "E-mail Icon", "jjcpacalso@uc-bcf.edu.ph")
        )
        TeamBusinessCard(logoWithNameInfo, contactInfoList)
    }
}