package br.com.enterprise.mobileparking.domain.entity

import java.math.BigDecimal

data class TempoPermanencia (
    val tempo: Int,
    val valor: BigDecimal
)