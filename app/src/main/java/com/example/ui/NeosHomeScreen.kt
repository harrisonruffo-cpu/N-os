package com.example.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.model.HOTMART_RAIO_X_URL
import com.example.ui.components.FaqSection
import com.example.ui.components.FooterSection
import com.example.ui.components.HeaderSection
import com.example.ui.components.HeroSection
import com.example.ui.components.RaioXHighlightSection
import com.example.ui.components.ServicesSection
import com.example.ui.components.StickyCtaBar
import com.example.ui.components.TaxSimulatorSection
import com.example.ui.components.TestimonialsSection
import com.example.ui.components.WhyNeosSection
import com.example.ui.theme.NeosNavyDark
import kotlinx.coroutines.launch

@Composable
fun NeosHomeScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val onOpenSaibaMais: () -> Unit = {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(HOTMART_RAIO_X_URL)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            scope.launch {
                snackbarHostState.showSnackbar("Abrindo página segura do Raio-X Tributário na Hotmart...")
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = NeosNavyDark,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            StickyCtaBar(
                onSaibaMaisClick = onOpenSaibaMais
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(NeosNavyDark),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 640.dp) // Maintain ideal proportions on tablets / foldables
                    .verticalScroll(scrollState)
                    .padding(bottom = innerPadding.calculateBottomPadding())
            ) {
                // Top Status Bar Spacer + Header
                Spacer(modifier = Modifier.statusBarsPadding())

                HeaderSection(
                    onSaibaMaisClick = onOpenSaibaMais
                )

                Spacer(modifier = Modifier.height(16.dp))

                HeroSection(
                    onSaibaMaisClick = onOpenSaibaMais
                )

                Spacer(modifier = Modifier.height(32.dp))

                RaioXHighlightSection(
                    onSaibaMaisClick = onOpenSaibaMais
                )

                Spacer(modifier = Modifier.height(36.dp))

                TaxSimulatorSection(
                    onSaibaMaisClick = onOpenSaibaMais
                )

                Spacer(modifier = Modifier.height(36.dp))

                WhyNeosSection()

                Spacer(modifier = Modifier.height(36.dp))

                ServicesSection(
                    onServiceClick = onOpenSaibaMais
                )

                Spacer(modifier = Modifier.height(36.dp))

                TestimonialsSection()

                Spacer(modifier = Modifier.height(36.dp))

                FaqSection()

                Spacer(modifier = Modifier.height(32.dp))

                FooterSection(
                    onSaibaMaisClick = onOpenSaibaMais
                )
            }
        }
    }
}
