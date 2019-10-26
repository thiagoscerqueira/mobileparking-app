package br.com.enterprise.mobileparking.domain.repository

import br.com.enterprise.mobileparking.domain.entity.TempoPermanencia

interface TempoPermanenciaRepository {
    fun listarTemposPermanencia() : List<TempoPermanencia>
}