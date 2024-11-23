package com.example.ttnguoivoimay06
// MainActivity.kt
// MainActivity.kt
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class NetworkStatusReceiver(private val onNetworkChanged: (Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

        val isConnected = networkCapabilities?.run {
            hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } ?: false

        onNetworkChanged(isConnected)
    }
}

class MainActivity : ComponentActivity() {
    private lateinit var networkStatusReceiver: NetworkStatusReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NetworkStatusTheme {
                NetworkStatusScreen()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        networkStatusReceiver = NetworkStatusReceiver { isConnected ->
            val message = if (isConnected) "Đã kết nối mạng" else "Không có kết nối mạng"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        registerReceiver(networkStatusReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkStatusReceiver)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkStatusScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trạng Thái Mạng") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF407F3E),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Kiểm Tra Kết Nối Mạng",
                color = Color(0xFF89B449),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun NetworkStatusTheme(content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        primary = Color(0xFF407F3E),
        onPrimary = Color.White,
        secondary = Color(0xFF89B449),
        onSecondary = Color.White,
        background = Color(0xFFE7E0C4),
        onBackground = Color.Black,
        surface = Color(0xFFE68A8C),
        onSurface = Color.Black,
        error = Color(0xFFDB1468),
        onError = Color.White,
        primaryContainer = Color(0xFF407F3E),
        onPrimaryContainer = Color.White,
        secondaryContainer = Color(0xFF89B449),
        onSecondaryContainer = Color.White,
        tertiary = Color(0xFFDBD468),
        onTertiary = Color.Black,
        tertiaryContainer = Color(0xFFDBD468),
        onTertiaryContainer = Color.Black
    )

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}