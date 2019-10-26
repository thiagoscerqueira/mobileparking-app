package br.com.enterprise.mobileparking.app.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

object MoedaUtil {

    val PORTUGUES = "pt"
    val BRASIL = "br"
    val FORMATO_PADRAO = "R$"
    val FORMATO_COM_ESPACO = "R$ "

    fun formataParaBrasileiro(valor: BigDecimal?): String {
        if (valor == null) {
            return ""
        }

        val formatoBrasileiro = DecimalFormat.getCurrencyInstance(
            Locale(PORTUGUES, BRASIL)
        )
        return formatoBrasileiro
            .format(valor)
            .replace(FORMATO_PADRAO, FORMATO_COM_ESPACO)
    }

}
