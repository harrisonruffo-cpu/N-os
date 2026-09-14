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
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TaxDataRepository
import com.example.model.Testimonial
import com.example.ui.theme.NeosAmber
import com.example.ui.theme.NeosCyan
import com.example.ui.theme.NeosGreenAccent
import com.example.ui.theme.NeosNavyCard
import com.example.ui.theme.NeosNavyCardBorder
import com.example.ui.theme.NeosNavySurface
import com.example.ui.theme.NeosTextMuted
import com.example.ui.theme.NeosTextPrimary
import com.example.ui.theme.NeosTextSecondary

@Composable
fun TestimonialsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "DEPOIMENTOS",
            color = NeosCyan,
            fontSize = 11.5.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Quem confiou na Néos viu a economia na prática",
            color = NeosTextPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 28.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Histórias reais de quem transformou despesas tributárias em caixa para reinvestir.",
            color = NeosTextSecondary,
            fontSize = 13.5.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TaxDataRepository.testimonials.forEach { item ->
                TestimonialCard(item)
            }
        }
    }
}

@Composable
private fun TestimonialCard(
    testimonial: Testimonial
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(NeosNavySurface)
            .border(1.dp, NeosNavyCardBorder, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(NeosNavyCard),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = testimonial.name.take(1),
                            color = NeosCyan,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = testimonial.name,
                                color = NeosTextPrimary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = "Verificado",
                                tint = NeosCyan,
                                modifier = Modifier.size(13.dp)
                            )
                        }
                        Text(
                            text = testimonial.role,
                            color = NeosTextMuted,
                            fontSize = 11.sp
                        )
                    }
                }

                // Stars
                Row(horizontalArrangement = Arrangement.spacedBy(1.dp)) {
                    repeat(testimonial.rating) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = NeosAmber,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "\"${testimonial.quote}\"",
                color = NeosTextSecondary,
                fontSize = 12.5.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(NeosGreenAccent.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Economia alcançada: ${testimonial.annualSavings}",
                    color = NeosGreenAccent,
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
