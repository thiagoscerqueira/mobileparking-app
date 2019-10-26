package br.com.enterprise.mobileparking.resources.network.fuel

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import br.com.enterprise.mobileparking.domain.exception.NetworkException
import br.com.enterprise.mobileparking.resources.network.EstacionamentoApi
import br.com.enterprise.mobileparking.resources.network.entity.ConsultaEstacionamentoResponse

class EstacionamentoFuelApi : EstacionamentoApi {
    override fun finalizarEstacionamento(estacionamentoUsuarioId: String)  =
        Fuel.post("/estacionamento/finaliza")
            .header("Content-Type" to "application/json")
            .body("{\"estacionamentoUsuarioId\":\"${estacionamentoUsuarioId}\"}")
            .responseObject<ConsultaEstacionamentoResponse>().let {
                if (it.second.statusCode != 200) {
                    throw NetworkException()
                }
            }

    override fun buscarVigente(usuarioId: String) = Fuel.get("/estacionamento/vigente?usuarioId=$usuarioId")
        .responseObject<ConsultaEstacionamentoResponse>().let {
            when (it.second.statusCode) {
                200, 304 -> it.third.get()
                404 -> null
                else -> throw NetworkException()
            }
        }

    override fun iniciarEstacionamento(usuarioId: String, placaVeiculo: String, tempoPermanencia: Int) =
        Fuel.post("/estacionamento")
            .header("Content-Type" to "application/json")
            .body("{\"usuario\":\"${usuarioId}\",\"placaVeiculo\":\"$placaVeiculo\",\"tempoPermanencia\":$tempoPermanencia}")
            .responseObject<ConsultaEstacionamentoResponse>().let {
                when (it.second.statusCode) {
                    200, 304 -> it.third.get()
                    404 -> null
                    else -> throw NetworkException()
                }
            }
}