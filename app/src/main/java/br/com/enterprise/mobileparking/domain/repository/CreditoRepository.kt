package br.com.enterprise.mobileparking.domain.repository

import java.math.BigDecimal

interface CreditoRepository {
    fun adicionaCredito(usuarioId: String, valor: BigDecimal)
}