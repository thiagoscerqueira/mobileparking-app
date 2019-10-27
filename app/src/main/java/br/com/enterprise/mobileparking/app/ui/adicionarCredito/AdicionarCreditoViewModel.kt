package br.com.enterprise.mobileparking.app.ui.adicionarCredito

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.enterprise.mobileparking.app.ui.BackgroundViewModel
import br.com.enterprise.mobileparking.domain.repository.CreditoRepository
import java.math.BigDecimal
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AdicionarCreditoViewModel(
    executor: ExecutorService = Executors.newSingleThreadExecutor(),
    private val repository: CreditoRepository
) : BackgroundViewModel (executor){

    private val _adicionaCreditos = MutableLiveData<Unit>()
    private val _erro = MutableLiveData<Throwable>()

    val adicionaCreditos: LiveData<Unit> get() =_adicionaCreditos
    val erro: LiveData<Throwable> get() = _erro

    val valor: ObservableField<String> = ObservableField()

    fun adicionarCreditos() {
        doInBackground {
            try {
                valor.get()?.let {  _adicionaCreditos.postValue(repository.adicionaCredito("thiago",
                    BigDecimal(it.replace(",", ".")))) }
                    ?: throw Throwable("Valor deve ser informado!")
            } catch (e: Throwable) {
                _erro.postValue(e)
                e.message?.let { Log.e("adicionarCreditos()", it) }
            } finally {
            }
        }
    }

}