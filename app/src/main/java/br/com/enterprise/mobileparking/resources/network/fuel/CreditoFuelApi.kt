package br.com.enterprise.mobileparking.resources.network.fuel

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import br.com.enterprise.mobileparking.domain.exception.NetworkException
import br.com.enterprise.mobileparking.resources.network.CreditoApi
import br.com.enterprise.mobileparking.resources.network.entity.ConsultaCreditoResponse
import java.math.BigDecimal
import java.util.*

class CreditoFuelApi : CreditoApi {
    override fun adicionaCredito(usuarioId: String, valor: BigDecimal) =
        Fuel.post("/creditos")
            .header("Content-Type" to "application/json")
            .body("{\"usuario\":\"${usuarioId}\",\"valor\":$valor}")
            .responseObject<Objects>().let {
                if (it.second.statusCode != 200) {
                    throw NetworkException()
                }
            }

    override fun buscarCredito(usuarioId: String) = Fuel.get("/creditos?usuarioId=$usuarioId")
        .responseObject<ConsultaCreditoResponse>().let {
            when (it.second.statusCode) {
                200, 304 -> it.third.get()
                404 -> null
                else -> throw NetworkException()
            }
        }

}