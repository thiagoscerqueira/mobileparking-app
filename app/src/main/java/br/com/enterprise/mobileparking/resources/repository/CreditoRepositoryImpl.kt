package br.com.enterprise.mobileparking.resources.repository

import br.com.enterprise.mobileparking.domain.repository.CreditoRepository
import br.com.enterprise.mobileparking.resources.network.CreditoApi
import java.math.BigDecimal

class CreditoRepositoryImpl(private val api: CreditoApi): CreditoRepository {
    override fun adicionaCredito(usuarioId: String, valor: BigDecimal) = api.adicionaCredito(usuarioId, valor)

}