package br.com.enterprise.mobileparking.resources.network.entity

import br.com.enterprise.mobileparking.domain.entity.Estacionamento
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class ConsultaEstacionamentoResponse(
    val estacionamentoUsuarioId: String,
    val usuarioId: String,
    val inicio: String,
    val tempoRestante: String,
    val expirado: Boolean
)

fun ConsultaEstacionamentoResponse.toDomain(): Estacionamento {
    return Estacionamento(
        estacionamentoUsuarioId = this.estacionamentoUsuarioId,
        inicio = LocalDateTime.parse(this.inicio, DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        placaVeiculo = "GDX-9999",
        tempoRestante = Duration.parse(this.tempoRestante)
    )
}