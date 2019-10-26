package br.com.enterprise.mobileparking.resources.network.entity

import br.com.enterprise.mobileparking.domain.entity.Usuario
import java.math.BigDecimal

data class ConsultaCreditoResponse(
    val usuario: String,
    val saldo: BigDecimal
)

fun ConsultaCreditoResponse.toDomain(): Usuario {
    return Usuario(
        id = this.usuario,
        nome = "Thiago",
        saldoAtualizado = this.saldo
    )
}