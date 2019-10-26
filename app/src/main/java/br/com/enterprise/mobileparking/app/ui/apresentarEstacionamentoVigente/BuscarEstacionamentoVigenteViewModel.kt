package br.com.enterprise.mobileparking.app.ui.apresentarEstacionamentoVigente

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.enterprise.mobileparking.app.ui.BackgroundViewModel
import br.com.enterprise.mobileparking.domain.entity.Estacionamento
import br.com.enterprise.mobileparking.domain.entity.Usuario
import br.com.enterprise.mobileparking.domain.repository.EstacionamentoRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BuscarEstacionamentoVigenteViewModel(
    executor: ExecutorService = Executors.newSingleThreadExecutor(),
    private val repository: EstacionamentoRepository
) : BackgroundViewModel (executor){

    private val _carregando = MutableLiveData<Boolean>()
    private val _estacionamentoVigente = MutableLiveData<Estacionamento?>()
    private val _erro = MutableLiveData<Throwable>()

    private val _chamandoFinalizaEstacionamento = MutableLiveData<Boolean>()

    val carregando: LiveData<Boolean> get() =_carregando
    val estacionamentoVigente: LiveData<Estacionamento?> get() = _estacionamentoVigente
    val erro: LiveData<Throwable> get() = _erro

    val chamandoFinalizaEstacionamento : LiveData<Boolean> get() = _chamandoFinalizaEstacionamento

    fun buscarEstacionamentoVigente(usuario: Usuario) {
        doInBackground {
            try {
                _carregando.postValue(true);
                _estacionamentoVigente.postValue(repository.buscarVigente(usuario.id))
            } catch (e: Throwable) {
                _erro.postValue(e)
                e.message?.let { Log.e("buscarEstacionamentoVigente()", it) }
            } finally {
                _carregando.postValue(false)
            }
        }
    }

    fun finalizarEstacionamento(estacionamentoVigente: Estacionamento) {
        doInBackground {
            repository.finalizarEstacionamento(estacionamentoVigente.estacionamentoUsuarioId)
            _chamandoFinalizaEstacionamento.postValue(true)
        }
    }

    fun atualizaEstacionamentoVigente(estacionamentoVigente: Estacionamento) {
        doInBackground {
            _estacionamentoVigente.postValue(estacionamentoVigente)
        }
    }

}