package br.com.enterprise.mobileparking.resources.repository

import br.com.enterprise.mobileparking.domain.repository.EstacionamentoRepository
import br.com.enterprise.mobileparking.resources.network.EstacionamentoApi
import br.com.enterprise.mobileparking.resources.network.entity.toDomain

class EstacionamentoRepositoryImpl(
    private val api: EstacionamentoApi
) : EstacionamentoRepository {

    override fun finalizarEstacionamento(estacionamentoUsuarioId: String)
            = api.finalizarEstacionamento(estacionamentoUsuarioId)

    override fun iniciarEstacionamento(usuarioId: String, placaVeiculo: String, tempoPermanencia: Int) =
        api.iniciarEstacionamento(usuarioId, placaVeiculo, tempoPermanencia).let { response ->
            if (response != null) {
                response.toDomain()
            } else {
                null
            }
        }

    override fun buscarVigente(usuarioId: String) = api.buscarVigente(usuarioId).let { response ->
        if (response != null) {
            response.toDomain()
        } else {
            null
        }
    }

}