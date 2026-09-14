package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.RegimeComparison
import com.example.model.TaxDataRepository
import com.example.ui.theme.NeosAmber
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
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TaxSimulatorSection(
    onSaibaMaisClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var monthlyRevenue by remember { mutableDoubleStateOf(15000.0) }
    val regimes = remember(monthlyRevenue) {
        TaxDataRepository.calculateRegimes(monthlyRevenue)
    }

    val ptBr = remember { Locale("pt", "BR") }
    val currencyFormat = remember { NumberFormat.getCurrencyInstance(ptBr) }

    // Find difference between Pessoa Física and the best regime to show savings
    val pfTax = regimes.find { it.title == "Pessoa Física" }?.estimatedTaxMonthly ?: 0.0
    val bestRegime = regimes.filter { !it.title.contains("Pessoa Física") }.minByOrNull { it.estimatedTaxMonthly }
    val bestTax = bestRegime?.estimatedTaxMonthly ?: 0.0
    val monthlySavings = (pfTax - bestTax).coerceAtLeast(0.0)
    val annualSavings = monthlySavings * 12

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Section Title
        Text(
            text = "Simulador Prévio de Regimes",
            color = NeosCyan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Veja a diferença que a escolha certa faz no seu bolso",
            color = NeosTextPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 28.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Arraste o valor para simular seu faturamento mensal aproximado e compare a carga tributária estimada em cada modelo:",
            color = NeosTextSecondary,
            fontSize = 13.5.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Revenue Control Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(NeosNavySurface)
                .border(1.dp, NeosNavyCardBorder, RoundedCornerShape(18.dp))
                .padding(18.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Faturamento Mensal:",
                        color = NeosTextSecondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = currencyFormat.format(monthlyRevenue),
                        color = NeosCyan,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Slider(
                    value = monthlyRevenue.toFloat(),
                    onValueChange = { monthlyRevenue = it.toDouble() },
                    valueRange = 2000f..80000f,
                    steps = 38,
                    colors = SliderDefaults.colors(
                        thumbColor = NeosCyan,
                        activeTrackColor = NeosCyan,
                        inactiveTrackColor = NeosNavyCardBorder
                    ),
                    modifier = Modifier.testTag("revenue_slider")
                )

                // Quick Presets
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    PresetChip(
                        label = "R$ 5k",
                        selected = monthlyRevenue == 5000.0,
                        onClick = { monthlyRevenue = 5000.0 },
                        modifier = Modifier.weight(1f)
                    )
                    PresetChip(
                        label = "R$ 15k",
                        selected = monthlyRevenue == 15000.0,
                        onClick = { monthlyRevenue = 15000.0 },
                        modifier = Modifier.weight(1f)
                    )
                    PresetChip(
                        label = "R$ 30k",
                        selected = monthlyRevenue == 30000.0,
                        onClick = { monthlyRevenue = 30000.0 },
                        modifier = Modifier.weight(1f)
                    )
                    PresetChip(
                        label = "R$ 60k",
                        selected = monthlyRevenue == 60000.0,
                        onClick = { monthlyRevenue = 60000.0 },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Annual Savings Highlight Card
        if (annualSavings > 500) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                NeosGreenAccent.copy(alpha = 0.18f),
                                NeosBlue.copy(alpha = 0.15f)
                            )
                        )
                    )
                    .border(1.dp, NeosGreenAccent.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(NeosGreenAccent.copy(alpha = 0.25f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AttachMoney,
                            contentDescription = null,
                            tint = NeosGreenAccent,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "POTENCIAL DE ECONOMIA:",
                            color = NeosGreenAccent,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "Até ${currencyFormat.format(annualSavings)} / ano",
                            color = NeosTextPrimary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "ao estruturar sua atividade no regime tributário adequado.",
                            color = NeosTextSecondary,
                            fontSize = 11.5.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Regimes List
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            regimes.forEach { item ->
                RegimeComparisonCard(
                    regime = item,
                    currencyFormat = currencyFormat
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Saiba Mais CTA for Detailed Diagnostic
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(NeosNavyCard)
                .border(1.dp, NeosCyan.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Quer o diagnóstico aprofundado com análise de CNAEs, Fator R e pró-labore?",
                    color = NeosTextPrimary,
                    fontSize = 13.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onSaibaMaisClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NeosCyan,
                        contentColor = NeosNavyDark
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("simulator_saiba_mais_btn")
                ) {
                    Text(
                        text = "GARANTIR RAIO-X TRIBUTÁRIO (R$ 47)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PresetChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) NeosCyan else NeosNavyCard)
            .border(
                1.dp,
                if (selected) NeosCyan else NeosNavyCardBorder,
                RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = if (selected) NeosNavyDark else NeosTextSecondary,
            fontSize = 11.5.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
        )
    }
}

@Composable
private fun RegimeComparisonCard(
    regime: RegimeComparison,
    currencyFormat: NumberFormat
) {
    val borderColor = when {
        regime.isBestChoice -> NeosGreenAccent
        regime.alertWarning != null -> NeosAmber
        else -> NeosNavyCardBorder
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(NeosNavySurface)
            .border(1.dp, borderColor, RoundedCornerShape(14.dp))
            .padding(14.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(
                                if (regime.isBestChoice) NeosGreenAccent.copy(alpha = 0.2f)
                                else NeosNavyCard
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = regime.icon,
                            contentDescription = null,
                            tint = if (regime.isBestChoice) NeosGreenAccent else NeosCyan,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = regime.title,
                                color = NeosTextPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (regime.isBestChoice) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(NeosGreenAccent)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "MAIS VANTAJOSO",
                                        color = NeosNavyDark,
                                        fontSize = 8.5.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                            }
                        }
                        Text(
                            text = regime.subtitle,
                            color = NeosTextMuted,
                            fontSize = 11.sp
                        )
                    }
                }

                // Estimated Tax Value
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = currencyFormat.format(regime.estimatedTaxMonthly),
                        color = if (regime.isBestChoice) NeosGreenAccent else NeosTextPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = String.format(Locale.US, "~%.1f%% efetivo", regime.effectiveRatePercent),
                        color = NeosTextSecondary,
                        fontSize = 10.5.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = regime.description,
                color = NeosTextSecondary,
                fontSize = 11.5.sp,
                lineHeight = 16.sp
            )

            // Warning / Alert if present
            if (regime.alertWarning != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(NeosAmber.copy(alpha = 0.12f))
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = NeosAmber,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = regime.alertWarning,
                        color = NeosAmber,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 15.sp
                    )
                }
            }
        }
    }
}
