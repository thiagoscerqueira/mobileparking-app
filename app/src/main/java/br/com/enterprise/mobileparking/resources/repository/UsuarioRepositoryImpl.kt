package br.com.enterprise.mobileparking.resources.repository

import br.com.enterprise.mobileparking.domain.repository.UsuarioRepository
import br.com.enterprise.mobileparking.resources.network.CreditoApi
import br.com.enterprise.mobileparking.resources.network.entity.toDomain

class UsuarioRepositoryImpl(private val api: CreditoApi): UsuarioRepository {
    override fun buscarDadosUsuario(usuarioId: String) = api.buscarCredito(usuarioId).let { response ->
        if (response != null) {
            val usuario = response.toDomain()
            usuario.nome = "Thiago"
            usuario
        } else {
            null
        }
    }
}