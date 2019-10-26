package br.com.enterprise.mobileparking.resources.network

import br.com.enterprise.mobileparking.resources.network.entity.ConsultarTempoPermanenciaResponse

interface TempoPermanenciaApi {
    fun listarTemposPermanencia() : List<ConsultarTempoPermanenciaResponse>
}