package br.com.enterprise.mobileparking.resources.network.entity

import br.com.enterprise.mobileparking.domain.entity.TempoPermanencia
import java.math.BigDecimal

data class ConsultarTempoPermanenciaResponse (
    val tempo: Int,
    val valor: BigDecimal
)

fun ConsultarTempoPermanenciaResponse.toDomain(): TempoPermanencia {
    return TempoPermanencia(tempo = this.tempo, valor = this.valor)
}