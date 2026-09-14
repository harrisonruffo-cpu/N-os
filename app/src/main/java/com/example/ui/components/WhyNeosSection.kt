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
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun WhyNeosSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Tagline Pill
        Text(
            text = "DIFERENCIAL NÉOS",
            color = NeosCyan,
            fontSize = 11.5.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Tecnologia de fintech, com a empatia e o cuidado humano que você merece.",
            color = NeosTextPrimary,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            lineHeight = 28.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sabemos que contabilidade não é apenas sobre números e DARFs: é sobre os seus sonhos e o sustento da sua família. Por isso descomplicamos tudo.",
            color = NeosTextSecondary,
            fontSize = 13.5.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DifferentiatorCard(
                icon = Icons.AutoMirrored.Filled.Chat,
                title = "Atendimento Humanizado via WhatsApp",
                description = "Chega de falar com robôs e abrir chamados que demoram dias. Você fala diretamente com um consultor contábil que conhece seu nome e a realidade da sua empresa."
            )

            DifferentiatorCard(
                icon = Icons.Default.Psychology,
                title = "Comunicação Simples e Clara",
                description = "Sem jargões tributários herméticos. Explicamos em linguagem acessível e direta exatamente o que precisa ser feito e por quê."
            )

            DifferentiatorCard(
                icon = Icons.Default.Gavel,
                title = "Segurança Jurídica & Elisão Legal",
                description = "Planejamento tributário 100% amparado na legislação da Receita Federal. Você economiza com total tranquilidade para dormir em paz."
            )

            DifferentiatorCard(
                icon = Icons.Default.Smartphone,
                title = "100% Digital e Sem Papelada",
                description = "Emissão de notas, certidões e guias de impostos acessíveis na palma da mão, onde quer que você esteja."
            )
        }
    }
}

@Composable
private fun DifferentiatorCard(
    icon: ImageVector,
    title: String,
    description: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(NeosNavySurface)
            .border(1.dp, NeosNavyCardBorder, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NeosBlue.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = NeosCyan,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = title,
                    color = NeosTextPrimary,
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    color = NeosTextSecondary,
                    fontSize = 12.5.sp,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
