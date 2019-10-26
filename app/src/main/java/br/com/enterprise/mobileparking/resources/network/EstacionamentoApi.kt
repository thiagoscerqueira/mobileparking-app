package br.com.enterprise.mobileparking.resources.network

import br.com.enterprise.mobileparking.resources.network.entity.ConsultaEstacionamentoResponse

interface EstacionamentoApi {
    fun buscarVigente(usuarioId: String): ConsultaEstacionamentoResponse?

    fun iniciarEstacionamento(
        usuarioId: String,
        placaVeiculo: String,
        tempoPermanencia: Int
    ): ConsultaEstacionamentoResponse?

    fun finalizarEstacionamento (estacionamentoUsuarioId: String)
}