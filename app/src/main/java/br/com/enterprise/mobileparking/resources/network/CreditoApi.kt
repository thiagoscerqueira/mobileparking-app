package br.com.enterprise.mobileparking.resources.network

import br.com.enterprise.mobileparking.resources.network.entity.ConsultaCreditoResponse
import java.math.BigDecimal

interface CreditoApi {
    fun buscarCredito(usuarioId: String): ConsultaCreditoResponse?

    fun adicionaCredito(usuarioId: String, valor: BigDecimal)
}