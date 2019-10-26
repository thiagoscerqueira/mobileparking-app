package br.com.enterprise.mobileparking.domain.repository

import br.com.enterprise.mobileparking.domain.entity.Estacionamento

interface EstacionamentoRepository {
    fun buscarVigente(usuarioId: String): Estacionamento?

    fun iniciarEstacionamento(
        usuarioId: String,
        placaVeiculo: String,
        tempoPermanencia: Int
    ): Estacionamento?

    fun finalizarEstacionamento (estacionamentoUsuarioId: String)
}