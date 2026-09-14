package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TaxDataRepository
import com.example.ui.theme.NeosAmber
import com.example.ui.theme.NeosBlue
import com.example.ui.theme.NeosBlueDark
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
fun RaioXHighlightSection(
    onSaibaMaisClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(16.dp, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = NeosNavySurface
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 1.5.dp,
            brush = Brush.verticalGradient(
                listOf(NeosCyan.copy(alpha = 0.8f), NeosBlue.copy(alpha = 0.2f))
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            NeosBlueDark.copy(alpha = 0.4f),
                            NeosNavySurface
                        )
                    )
                )
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Badge
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(NeosNavyCard)
                    .border(1.dp, NeosCyan.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = null,
                    tint = NeosCyan,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "SIMULADOR EXCLUSIVO",
                    color = NeosCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Section Main Headline
            Text(
                text = "Raio-X\nTributário",
                color = NeosTextPrimary,
                fontSize = 34.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                lineHeight = 38.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Descubra, compare e escolha o melhor regime para o seu negócio.",
                color = NeosCyan,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Entenda seus números antes de tomar sua próxima decisão. Não pague 1 real a mais do que a lei exige.",
                color = NeosTextSecondary,
                fontSize = 13.5.sp,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Laptop/Screen Mockup representing the ad screenshot
            LaptopMockupCard()

            Spacer(modifier = Modifier.height(24.dp))

            // The 4 Pillars Grid
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    PillarTile(
                        icon = Icons.Default.Calculate,
                        title = "Compare cenários reais",
                        description = "PF, MEI, Simples e Presumido",
                        modifier = Modifier.weight(1f)
                    )
                    PillarTile(
                        icon = Icons.Default.TrendingDown,
                        title = "Veja o impacto nos tributos",
                        description = "Menos retenção, mais caixa",
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    PillarTile(
                        icon = Icons.Default.Shield,
                        title = "Mais segurança nas decisões",
                        description = "100% amparado na lei",
                        modifier = Modifier.weight(1f)
                    )
                    PillarTile(
                        icon = Icons.Default.Speed,
                        title = "Simples, rápido e prático",
                        description = "Resultados em poucos cliques",
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            // Pricing Callout Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(NeosBlueDark, NeosNavyCard)
                        )
                    )
                    .border(1.dp, NeosCyan.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                    .padding(18.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "APENAS",
                        color = NeosCyan,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "R$ 47,00",
                        color = NeosTextPrimary,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "UM INVESTIMENTO INTELIGENTE PARA O SEU FUTURO.",
                        color = NeosTextSecondary,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.5.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Prominent Camouflaged Saiba Mais CTA
                    Button(
                        onClick = onSaibaMaisClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NeosCyan,
                            contentColor = NeosNavyDark
                        ),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("saiba_mais_raio_x_btn")
                    ) {
                        Text(
                            text = "SAIBA MAIS",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = NeosGreenAccent,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Checkout Seguro Hotmart • Acesso Imediato",
                            color = NeosGreenAccent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PillarTile(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(NeosNavyCard)
            .border(1.dp, NeosNavyCardBorder, RoundedCornerShape(14.dp))
            .padding(12.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(NeosCyan.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = NeosCyan,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                color = NeosTextPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                color = NeosTextSecondary,
                fontSize = 10.5.sp,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun LaptopMockupCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0F172A))
            .border(1.dp, NeosCyan.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
            .padding(14.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Screen top bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Color(0xFFEF4444)))
                    Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Color(0xFFF59E0B)))
                    Box(modifier = Modifier.size(7.dp).clip(CircleShape).background(Color(0xFF10B981)))
                }
                Text(
                    text = "Néos • Simulador de Regimes",
                    color = NeosTextMuted,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Regimes comparative micro-cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                MockupRegimePill("Pessoa Física", Icons.Default.Person, modifier = Modifier.weight(1f))
                MockupRegimePill("Nano / MEI", Icons.Default.RocketLaunch, modifier = Modifier.weight(1f))
                MockupRegimePill("Simples Nac.", Icons.Default.Calculate, modifier = Modifier.weight(1f), isHighlighted = true)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Mais clareza para decisões mais seguras.",
                color = NeosCyan,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun MockupRegimePill(
    name: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    isHighlighted: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isHighlighted) NeosBlue.copy(alpha = 0.4f) else NeosNavyCard)
            .border(
                1.dp,
                if (isHighlighted) NeosCyan else NeosNavyCardBorder,
                RoundedCornerShape(8.dp)
            )
            .padding(vertical = 8.dp, horizontal = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isHighlighted) NeosCyan else NeosTextSecondary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                color = if (isHighlighted) NeosTextPrimary else NeosTextSecondary,
                fontSize = 9.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}
