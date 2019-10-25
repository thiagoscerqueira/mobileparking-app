package br.com.enterprise.mobileparking.domain.entity

import java.time.Duration
import java.time.LocalDateTime

data class Estacionamento (
    val estacionamentoUsuarioId: String,
    val placaVeiculo: String,
    val inicio: LocalDateTime,
    val tempoRestante: Duration,
    val dataHoraPesquisa: LocalDateTime = LocalDateTime.now()
) {

    fun tempoRestanteAtualizado() : Duration {
        val tempoTranscorridoDesdePesquisa = Duration.between(dataHoraPesquisa, LocalDateTime.now())
        return tempoRestante.minus(tempoTranscorridoDesdePesquisa)
    }

    fun tempoFinalizado() : Boolean = tempoRestanteAtualizado().compareTo(Duration.ZERO) <= 0
}