package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.NeosBlue
import com.example.ui.theme.NeosCyan
import com.example.ui.theme.NeosGreenAccent
import com.example.ui.theme.NeosNavyCard
import com.example.ui.theme.NeosNavyCardBorder
import com.example.ui.theme.NeosNavyDark
import com.example.ui.theme.NeosNavySurface
import com.example.ui.theme.NeosTextMuted
import com.example.ui.theme.NeosTextPrimary
import com.example.ui.theme.NeosTextSecondary

@Composable
fun HeroSection(
    onSaibaMaisClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tagline Pill
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .background(NeosNavyCard)
                .border(1.dp, NeosCyan.copy(alpha = 0.35f), RoundedCornerShape(30.dp))
                .padding(horizontal = 14.dp, vertical = 7.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(NeosGreenAccent)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "PLANEJAMENTO HOJE, MAIS RESULTADOS AMANHÃ.",
                    color = NeosCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Main Title
        Text(
            text = "Contabilidade moderna com alma humana e precisão de fintech.",
            color = NeosTextPrimary,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            lineHeight = 35.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Subtitle
        Text(
            text = "Entenda seus números antes de tomar sua próxima decisão. Sem burocracia, sem surpresas no fim do mês e com a segurança de que o seu negócio está em boas mãos.",
            color = NeosTextSecondary,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(22.dp))

        // Consultant Picture Card (Visual reference from the user's video & banner)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, NeosNavyCardBorder, RoundedCornerShape(20.dp))
                .shadow(12.dp, RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_hero_consultant),
                contentDescription = "Consultor Especialista Néos Contábil",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 10f),
                contentScale = ContentScale.Crop
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                NeosNavyDark.copy(alpha = 0.85f),
                                NeosNavyDark
                            )
                        )
                    )
            )

            // Overlaid badges and message
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(NeosNavySurface.copy(alpha = 0.9f))
                        .border(1.dp, NeosCyan.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = NeosCyan,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Seu negócio em boas mãos",
                            color = NeosTextPrimary,
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Especialistas focados em reduzir seus tributos legalmente.",
                    color = NeosTextPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Trust Metrics Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(NeosNavySurface)
                .border(1.dp, NeosNavyCardBorder, RoundedCornerShape(14.dp))
                .padding(vertical = 14.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrustMetricItem(number = "R$ 47", label = "Raio-X Tributário")
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(30.dp)
                    .background(NeosNavyCardBorder)
            )
            TrustMetricItem(number = "+1.200", label = "Empresas atendidas")
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(30.dp)
                    .background(NeosNavyCardBorder)
            )
            TrustMetricItem(number = "4.9 ★", label = "Satisfação média")
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Hero Saiba Mais Action
        Button(
            onClick = onSaibaMaisClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = NeosCyan,
                contentColor = NeosNavyDark
            ),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .testTag("hero_saiba_mais_btn")
        ) {
            Text(
                text = "SAIBA MAIS SOBRE O RAIO-X TRIBUTÁRIO",
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = NeosGreenAccent,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Investimento acessível de apenas R$ 47,00 • Garantia Hotmart 7 dias",
                color = NeosTextMuted,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun TrustMetricItem(
    number: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number,
            color = NeosCyan,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            color = NeosTextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal
        )
    }
}
