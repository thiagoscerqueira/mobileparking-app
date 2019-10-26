package br.com.enterprise.mobileparking.domain.repository

import br.com.enterprise.mobileparking.domain.entity.Usuario

interface UsuarioRepository {
    fun buscarDadosUsuario(usuarioId: String): Usuario?
}