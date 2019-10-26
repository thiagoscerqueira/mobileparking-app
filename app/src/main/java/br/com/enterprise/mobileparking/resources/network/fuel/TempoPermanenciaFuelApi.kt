package br.com.enterprise.mobileparking.resources.network.fuel

import com.github.kittinunf.fuel.Fuel
import br.com.enterprise.mobileparking.domain.exception.NetworkException
import br.com.enterprise.mobileparking.resources.network.TempoPermanenciaApi
import com.github.kittinunf.fuel.gson.responseObject
import br.com.enterprise.mobileparking.resources.network.entity.ConsultarTempoPermanenciaResponse

class TempoPermanenciaFuelApi: TempoPermanenciaApi {
    override fun listarTemposPermanencia() = Fuel.get("/tempoPermanencia")
    .responseObject<List<ConsultarTempoPermanenciaResponse>>().let {
        when (it.second.statusCode) {
            200, 304 -> it.third.get()
            404 -> ArrayList()
            else -> throw NetworkException()
        }
    }
}