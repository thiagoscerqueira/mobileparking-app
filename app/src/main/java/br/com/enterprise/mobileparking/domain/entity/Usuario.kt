package br.com.enterprise.mobileparking.domain.entity

import java.io.Serializable
import java.math.BigDecimal

data class Usuario(
    val id: String,
    var nome: String,
    val saldoAtualizado: BigDecimal = BigDecimal.ZERO
): Serializable {
}