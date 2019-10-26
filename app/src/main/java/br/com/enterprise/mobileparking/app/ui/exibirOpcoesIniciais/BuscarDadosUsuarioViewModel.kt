package br.com.enterprise.mobileparking.app.ui.exibirOpcoesIniciais

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.enterprise.mobileparking.app.ui.BackgroundViewModel
import br.com.enterprise.mobileparking.domain.entity.Usuario
import br.com.enterprise.mobileparking.domain.repository.UsuarioRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BuscarDadosUsuarioViewModel(
    executor: ExecutorService = Executors.newSingleThreadExecutor(),
    private val repository: UsuarioRepository
) : BackgroundViewModel (executor){

    private val _carregando = MutableLiveData<Boolean>()
    private val _usuario = MutableLiveData<Usuario?>()
    private val _erro = MutableLiveData<Throwable>()

    val carregando: LiveData<Boolean> get() =_carregando
    val usuario: LiveData<Usuario?> get() = _usuario
    val erro: LiveData<Throwable> get() = _erro

    fun buscarDadosUsuario() {
        doInBackground {
            try {
                _carregando.postValue(true);
                _usuario.postValue(repository.buscarDadosUsuario("thiago"))
            } catch (e: Throwable) {
                _erro.postValue(e)
                e.message?.let { Log.e("buscarDadosUsuario()", it) }
            } finally {
                _carregando.postValue(false)
            }
        }
    }

}