package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            LemonadeTitle(title = stringResource(id = R.string.lemonade_title), modifier = Modifier)
            LemonadeClickableAndText()
        }
    }
}

@Composable
fun LemonadeTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        fontSize = 50.sp,
        lineHeight = 50.sp,
        modifier = Modifier
            .background(Color.Yellow)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun LemonadeClickableAndText(modifier: Modifier = Modifier) {
    var current by remember { mutableStateOf(1) }
    var lemonSqueezes by remember { mutableStateOf(0) }
    val imageResource = when (current) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val promptStringResource = when (current) {
        1 -> R.string.tap_lemon
        2 -> R.string.keep_tapping
        3 -> R.string.tap_lemonade
        else -> R.string.tap_glass
    }
    val contentStringResource = when (current) {
        1 -> R.string.lemon_tree_content_description
        2 -> R.string.lemon_content_description
        3 -> R.string.glass_content_description
        else -> R.string.empty_glass_content_description
    }
    Column(modifier = Modifier
        .wrapContentWidth(Alignment.CenterHorizontally)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Button(onClick = {
            if(current == 2) {
                lemonSqueezes += 1
                if(lemonSqueezes == 5) {
                    current += 1
                    lemonSqueezes = 0
                }
            }
            else if (current == 4) {
                current = 1
            }
            else {
                current += 1
            }
        },  modifier= Modifier
            .padding(top = 150.dp)) {
            Image(painter = painterResource(id = imageResource),
                contentDescription = stringResource(contentStringResource))
            }
        }
        Text(
            text = stringResource(promptStringResource),
            fontSize = 20.sp,
            lineHeight = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
}