package br.com.enterprise.mobileparking.app.ui.estacionar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.enterprise.mobileparking.R
import br.com.enterprise.mobileparking.app.ui.apresentarEstacionamentoVigente.EstacionamentoVigenteActivity
import br.com.enterprise.mobileparking.app.ui.exibirOpcoesIniciais.BuscarDadosUsuarioViewModel
import br.com.enterprise.mobileparking.app.util.MoedaUtil
import br.com.enterprise.mobileparking.databinding.ActivityEstacionarBinding
import br.com.enterprise.mobileparking.domain.entity.Usuario
import org.koin.androidx.viewmodel.ext.android.viewModel

class EstacionarActivity : AppCompatActivity() {

    private val viewModel by viewModel<EstacionarViewModel>()
    private val buscarDadosUsuarioViewModel by viewModel<BuscarDadosUsuarioViewModel>()
    private lateinit var binding: ActivityEstacionarBinding
    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscarDadosUsuarioViewModel.buscarDadosUsuario()
        configurarBinding()
        observarTemposPermanencia()
        observarUsuario()
    }

    private fun configurarBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_estacionar)
        binding.lifecycleOwner = this
        binding.buscarDadosUsuarioViewModel = buscarDadosUsuarioViewModel
        binding.reciclerTemposPermanencia.adapter = ListaTempoPermanenciaAdapter(::aoSelecionarTempoPermanencia)
        binding.moedaUtil = MoedaUtil
    }

    private fun observarTemposPermanencia() {
        viewModel.listaTempoPermanencia.observe(this, Observer {
            Log.d("TEMPOS_PERMANENCIA", it.toString())

            (binding.reciclerTemposPermanencia.adapter as ListaTempoPermanenciaAdapter)
                .atualizarTemposPermanencia(it)
        })
    }

    private fun observarUsuario() {
        buscarDadosUsuarioViewModel.usuario.observe(this, Observer {
            Log.d("USUARIO", it.toString())
            this.usuario = it
            viewModel.buscarTemposPermanencia()
        })
    }

    fun aoSelecionarTempoPermanencia(tempoPermanenciaSelecionado: Int) {
        this.usuario?.let {
            viewModel.estaciona(it.id, "GDX-9999", tempoPermanenciaSelecionado)
            viewModel.novoEstacionamento.observe(this, Observer {
                vaiParaTelaEstacionamentoVigente()
            })
        }
    }

    private fun vaiParaTelaEstacionamentoVigente() {
        val intent = Intent(this, EstacionamentoVigenteActivity::class.java)
        startActivity(intent)
    }
}
