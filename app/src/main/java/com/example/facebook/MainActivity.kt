package com.example.facebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facebook.ui.screens.FriendsScreen
import com.example.facebook.ui.screens.HomeScreen
import com.example.facebook.ui.screens.MarketplaceScreen
import com.example.facebook.ui.screens.ProfileScreen
import com.example.facebook.ui.theme.FacebookP
import com.example.facebook.ui.theme.FacebookPlaceholder
import com.example.facebook.ui.theme.FacebookSurface
import com.example.facebook.ui.theme.FacebookTextSecondary
import com.example.facebook.ui.theme.FacebookTheme

enum class FacebookTab(val title: String) {
    HOME("Inicio"),
    FRIENDS("Amigos"),
    MARKETPLACE("Market"),
    PROFILE("Perfil")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FacebookTheme {
                FacebookApp()
            }
        }
    }
}

@Composable
fun FacebookApp() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = FacebookTab.entries

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FacebookSurface)
            ) {
                Text(
                    text = "Facebook",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 40.dp,
                        bottom = 8.dp
                    )
                )

                PrimaryTabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = FacebookSurface,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    tabs.forEachIndexed { index, tab ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                Text(
                                    text = tab.title,
                                    fontWeight = if (selectedTabIndex == index) {
                                        FontWeight.Bold
                                    } else {
                                        FontWeight.Normal
                                    },
                                    color = if (selectedTabIndex == index) {
                                        MaterialTheme.colorScheme.primary

                                    } else {
                                        FacebookTextSecondary
                                    }
                                )
                            }
                        )
                    }
                }
                HorizontalDivider(color = FacebookPlaceholder)
            }
        }
    ) { innerPadding ->
        val screenModifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)

        when (tabs[selectedTabIndex]) {
            FacebookTab.HOME -> HomeScreen(modifier = screenModifier)
            FacebookTab.FRIENDS -> FriendsScreen(modifier = screenModifier)
            FacebookTab.MARKETPLACE -> MarketplaceScreen(modifier = screenModifier)
            FacebookTab.PROFILE -> ProfileScreen(modifier = screenModifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FacebookAppPreview() {
    FacebookTheme {
        FacebookApp()
    }
}
