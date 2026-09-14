package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CorporateFare
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.TrendingDown
import androidx.compose.ui.graphics.vector.ImageVector

const val HOTMART_RAIO_X_URL = "https://hotmart.com/pt-br/marketplace/produtos/hagsxd-raio-x-tributario-pf-ou-cnpj-vymqm/H107209521I"

data class RegimeComparison(
    val title: String,
    val subtitle: String,
    val estimatedTaxMonthly: Double,
    val effectiveRatePercent: Double,
    val description: String,
    val icon: ImageVector,
    val isBestChoice: Boolean = false,
    val alertWarning: String? = null
)

data class ServiceItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val highlight: String
)

data class Testimonial(
    val name: String,
    val role: String,
    val companyType: String,
    val annualSavings: String,
    val quote: String,
    val rating: Int = 5
)

data class FaqItem(
    val question: String,
    val answer: String
)

object TaxDataRepository {

    val pillars = listOf(
        Triple("Compare cenários reais", "Simule lado a lado PF, MEI, Simples Nacional e Lucro Presumido.", Icons.Default.Calculate),
        Triple("Veja o impacto nos tributos", "Descubra na prática quanto sobra líquido no seu bolso todo mês.", Icons.Default.TrendingDown),
        Triple("Mais segurança nas decisões", "Embase suas escolhas fiscais em conformidade com as regras da Receita.", Icons.Default.Shield),
        Triple("Simples, rápido e prático", "Diagnóstico direto ao ponto, sem termos técnicos ou burocracia inútil.", Icons.Default.Speed)
    )

    fun calculateRegimes(monthlyRevenue: Double): List<RegimeComparison> {
        // PF Carnê Leão + INSS autônomo (approx 20-27.5% depending on progressive brackets)
        val pfTax = when {
            monthlyRevenue <= 2259.20 -> 0.0
            monthlyRevenue <= 2826.65 -> (monthlyRevenue * 0.075) - 169.44 + (monthlyRevenue.coerceAtMost(7786.02) * 0.20)
            monthlyRevenue <= 3751.05 -> (monthlyRevenue * 0.15) - 381.44 + (monthlyRevenue.coerceAtMost(7786.02) * 0.20)
            monthlyRevenue <= 4664.68 -> (monthlyRevenue * 0.225) - 662.77 + (monthlyRevenue.coerceAtMost(7786.02) * 0.20)
            else -> (monthlyRevenue * 0.275) - 896.00 + (monthlyRevenue.coerceAtMost(7786.02) * 0.20)
        }.coerceAtLeast(0.0)

        // MEI (fixed tax approx R$ 75, max limit R$ 6.750/mo = 81.000/yr)
        val meiExceeded = monthlyRevenue > 6750.0
        val meiTax = if (meiExceeded) {
            monthlyRevenue * 0.06 // displaced to Simples
        } else {
            75.0
        }

        // Simples Nacional (Serviços - Anexo III com Fator R 28% ou Anexo V)
        // With Fator R strategy starting at 6%
        val simplesRate = when {
            monthlyRevenue <= 15000 -> 0.06
            monthlyRevenue <= 30000 -> 0.082
            monthlyRevenue <= 60000 -> 0.105
            else -> 0.135
        }
        val simplesTax = monthlyRevenue * simplesRate

        // Lucro Presumido (Federal ~ 11.33% + ISS ~ 2% a 5% -> ~13.33% to 16.33%)
        val presumidoRate = 0.1433
        val presumidoTax = monthlyRevenue * presumidoRate

        val isMeiIdeal = !meiExceeded && monthlyRevenue <= 6750.0
        val isSimplesIdeal = monthlyRevenue > 6750.0 && monthlyRevenue <= 50000.0

        return listOf(
            RegimeComparison(
                title = "Pessoa Física",
                subtitle = "Carnê-Leão + INSS Autônomo",
                estimatedTaxMonthly = pfTax,
                effectiveRatePercent = if (monthlyRevenue > 0) (pfTax / monthlyRevenue) * 100 else 0.0,
                description = "Tributação por IRPF até 27,5% mais recolhimento previdenciário. Altíssima retenção sobre o faturamento.",
                icon = Icons.Default.Person,
                isBestChoice = false,
                alertWarning = if (monthlyRevenue > 3000) "Atenção: Você pode estar perdendo até 20% do faturamento por não ter CNPJ." else null
            ),
            RegimeComparison(
                title = "MEI",
                subtitle = "Microempreendedor Individual",
                estimatedTaxMonthly = meiTax,
                effectiveRatePercent = if (monthlyRevenue > 0) (meiTax / monthlyRevenue) * 100 else 0.0,
                description = "Guia única DAS fixa. Excelente para início, limitado a R$ 81 mil anuais e restrito a certas profissões.",
                icon = Icons.Default.Savings,
                isBestChoice = isMeiIdeal,
                alertWarning = if (meiExceeded) "Limite ultrapassado! Seu faturamento exige desenquadramento obrigatório para ME." else null
            ),
            RegimeComparison(
                title = "Simples Nacional",
                subtitle = "Com planejamento de Fator R",
                estimatedTaxMonthly = simplesTax,
                effectiveRatePercent = simplesRate * 100,
                description = "Alíquota inicial reduzida a 6%. Reúne tributos federais, estaduais e municipais em uma única guia.",
                icon = Icons.Default.Calculate,
                isBestChoice = isSimplesIdeal,
                alertWarning = null
            ),
            RegimeComparison(
                title = "Lucro Presumido",
                subtitle = "Tributação sobre margem presumida",
                estimatedTaxMonthly = presumidoTax,
                effectiveRatePercent = presumidoRate * 100,
                description = "Base de cálculo pré-fixada. Indicado para faturamentos mais altos ou quando a folha não atinge 28%.",
                icon = Icons.Default.CorporateFare,
                isBestChoice = monthlyRevenue > 50000.0,
                alertWarning = null
            )
        )
    }

    val services = listOf(
        ServiceItem(
            title = "Raio-X Tributário",
            description = "Diagnóstico profundo para descobrir o melhor regime fiscal (PF x MEI x Simples x Presumido) e pagar o mínimo legal de impostos.",
            icon = Icons.Default.Calculate,
            highlight = "Apenas R$ 47"
        ),
        ServiceItem(
            title = "Abertura & Migração de CNPJ",
            description = "Processo 100% digital, sem filas ou burocracia. Cuidamos do contrato social, alvará e enquadramento ideal.",
            icon = Icons.Default.CorporateFare,
            highlight = "Descomplicado"
        ),
        ServiceItem(
            title = "Assessoria Contábil Mensal",
            description = "Contabilidade moderna com equipe especializada, entrega pontual de obrigações e canal direto via WhatsApp.",
            icon = Icons.Default.Shield,
            highlight = "Atendimento Humano"
        ),
        ServiceItem(
            title = "BPO & Planejamento Financeiro",
            description = "Gestão estratégica do fluxo de caixa, pró-labore, distribuição de lucros isenta e relatórios em tempo real.",
            icon = Icons.Default.TrendingDown,
            highlight = "Gestão Inteligente"
        )
    )

    val testimonials = listOf(
        Testimonial(
            name = "Lucas Mendes",
            role = "Desenvolvedor PJ & Consultor Tech",
            companyType = "Simples Nacional",
            annualSavings = "R$ 18.400 / ano",
            quote = "Eu faturava como pessoa física e deixava quase 27% para a Receita. Com o Raio-X Tributário da Néos, abri minha empresa com Fator R e reduzi para 6%. Mudou minha vida financeira."
        ),
        Testimonial(
            name = "Mariana Alencar",
            role = "Médica e Sócia de Clínica",
            companyType = "Lucro Presumido vs Simples",
            annualSavings = "R$ 32.000 / ano",
            quote = "O diferencial da Néos é a comunicação humana e transparente. Finalmente entendi para onde vai cada centavo e tive segurança total nas decisões da clínica."
        ),
        Testimonial(
            name = "Carlos Eduardo Souza",
            role = "E-commerce & Drop nacional",
            companyType = "Transição MEI para ME",
            annualSavings = "R$ 14.500 / ano",
            quote = "Estava com medo de estourar o MEI e ser multado. A Néos fez a transição suave, sem burocracia e com orientações simples e confiáveis."
        )
    )

    val faqs = listOf(
        FaqItem(
            question = "O que é o Raio-X Tributário?",
            answer = "É uma ferramenta e metodologia analítica que compara minuciosamente a sua atividade econômica e faturamento entre Pessoa Física, MEI, Simples Nacional (Anexo III e V) e Lucro Presumido. Você descobre exatamente quanto deveria pagar em cada opção e onde está a sua maior economia legítima."
        ),
        FaqItem(
            question = "O Raio-X serve para Pessoa Física ou só para quem já tem empresa?",
            answer = "Serve para ambos! Se você ainda atua no CPF (profissional autônomo, freelancer ou CLT com rendas extras), você verá o tamanho da economia ao formalizar. Se já tem CNPJ, você valida se está no regime correto ou pagando impostos a mais sem necessidade."
        ),
        FaqItem(
            question = "Por que o investimento é de apenas R$ 47,00?",
            answer = "Queremos que todo empreendedor brasileiro tenha acesso a uma análise financeira de alto nível, desmistificando a contabilidade e acabando com a cobrança indevida de tributos. É um valor simbólico para democratizar o planejamento fiscal."
        ),
        FaqItem(
            question = "Como recebo o acesso e suporte após clicar em Saiba Mais?",
            answer = "Ao clicar no botão Saiba Mais, você é direcionado para o ambiente seguro da Hotmart. O acesso ao Raio-X Tributário é liberado imediatamente após a confirmação do pagamento, com instruções passo a passo e garantia incondicional de 7 dias."
        ),
        FaqItem(
            question = "A Néos também cuida da contabilidade mensal da minha empresa?",
            answer = "Sim! Além do Raio-X Tributário, somos um escritório de contabilidade completo e moderno, que atende empresas de todo o Brasil com plataforma digital e especialistas humanos dedicados."
        )
    )
}
