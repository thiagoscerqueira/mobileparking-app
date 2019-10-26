package br.com.enterprise.mobileparking.resources.repository

import br.com.enterprise.mobileparking.domain.repository.TempoPermanenciaRepository
import br.com.enterprise.mobileparking.resources.network.TempoPermanenciaApi
import br.com.enterprise.mobileparking.resources.network.entity.toDomain

class TempoPermanenciaRepositoryImpl(private val api: TempoPermanenciaApi) : TempoPermanenciaRepository {
    override fun listarTemposPermanencia() = api.listarTemposPermanencia().map { it.toDomain() }
}