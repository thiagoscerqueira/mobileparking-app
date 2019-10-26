package br.com.enterprise.mobileparking.app.ui.estacionar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.enterprise.mobileparking.app.ui.BackgroundViewModel
import br.com.enterprise.mobileparking.domain.entity.Estacionamento
import br.com.enterprise.mobileparking.domain.entity.TempoPermanencia
import br.com.enterprise.mobileparking.domain.repository.EstacionamentoRepository
import br.com.enterprise.mobileparking.domain.repository.TempoPermanenciaRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EstacionarViewModel(
    executor: ExecutorService = Executors.newSingleThreadExecutor(),
    private val repository: TempoPermanenciaRepository,
    private val estacionamentoRepository: EstacionamentoRepository
) : BackgroundViewModel (executor){

    private val _carregando = MutableLiveData<Boolean>()
    private val _listaTempoPermanencia = MutableLiveData<List<TempoPermanencia>>()
    private val _erro = MutableLiveData<Throwable>()

    private val _carregandoEstaciona = MutableLiveData<Boolean>()
    private val _novoEstacionamento = MutableLiveData<Estacionamento>()
    private val _erroEstaciona = MutableLiveData<Throwable>()

    val carregando: LiveData<Boolean> get() =_carregando
    val listaTempoPermanencia: LiveData<List<TempoPermanencia>> get() = _listaTempoPermanencia
    val erro: LiveData<Throwable> get() = _erro

    val carregandoEstaciona: LiveData<Boolean> get() =_carregandoEstaciona
    val erroEstaciona: LiveData<Throwable> get() = _erroEstaciona
    val novoEstacionamento: LiveData<Estacionamento> get() = _novoEstacionamento

    fun buscarTemposPermanencia() {
        doInBackground {
            try {
                _carregando.postValue(true);
                _listaTempoPermanencia.postValue(repository.listarTemposPermanencia())
            } catch (e: Throwable) {
                _erro.postValue(e)
                e.message?.let { Log.e("buscarTemposPermanencia()", it) }
            } finally {
                _carregando.postValue(false)
            }
        }
    }

    fun estaciona(usuarioId: String, placaVeiculo: String, tempoPermanencia: Int) {
        doInBackground {
            try {
                _carregandoEstaciona.postValue(true);
                _novoEstacionamento.postValue(estacionamentoRepository.iniciarEstacionamento(usuarioId, placaVeiculo, tempoPermanencia))
            } catch (e: Throwable) {
                _erroEstaciona.postValue(e)
                e.message?.let { Log.e("estaciona()", it) }
            } finally {
                _carregandoEstaciona.postValue(false)
            }
        }
    }

}