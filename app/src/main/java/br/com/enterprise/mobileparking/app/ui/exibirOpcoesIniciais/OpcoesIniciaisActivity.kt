package br.com.enterprise.mobileparking.app.ui.exibirOpcoesIniciais

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.enterprise.mobileparking.R
import br.com.enterprise.mobileparking.app.ui.adicionarCredito.AdicionarCreditoActivity
import br.com.enterprise.mobileparking.app.ui.apresentarEstacionamentoVigente.BuscarEstacionamentoVigenteViewModel
import br.com.enterprise.mobileparking.app.ui.apresentarEstacionamentoVigente.EstacionamentoVigenteActivity
import br.com.enterprise.mobileparking.app.ui.estacionar.EstacionarActivity
import br.com.enterprise.mobileparking.app.util.MoedaUtil
import br.com.enterprise.mobileparking.databinding.ActivityOpcoesIniciaisBinding
import br.com.enterprise.mobileparking.domain.entity.Usuario
import org.koin.androidx.viewmodel.ext.android.viewModel

class OpcoesIniciaisActivity : AppCompatActivity() {
    private val viewModel by viewModel<BuscarDadosUsuarioViewModel>()
    private val buscarEstacionamentoVigenteViewModel by viewModel<BuscarEstacionamentoVigenteViewModel>()

    private lateinit var binding: ActivityOpcoesIniciaisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscarDadosUsuario()
        observarUsuario()
        configurarBinding()
    }

    private fun buscarDadosUsuario() {
        viewModel.buscarDadosUsuario()
    }

    private fun configurarBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_opcoes_iniciais)
        binding.buscarDadosUsuarioViewModel = viewModel
        binding.moedaUtil = MoedaUtil
        binding.clickHandler = ClickHandler(this)
        binding.lifecycleOwner = this
    }

    private fun observarUsuario() {
        viewModel.usuario.observe(this, Observer {
            it?.let {
                verificaSePossuiEstacionamentoVigenteERedirecionaUsuario(it)
            }
        })
    }

    private fun verificaSePossuiEstacionamentoVigenteERedirecionaUsuario(usuario: Usuario) {
        buscarEstacionamentoVigenteViewModel.buscarEstacionamentoVigente(usuario)
        buscarEstacionamentoVigenteViewModel.estacionamentoVigente.observe(this, Observer {
            it?.let {
                vaiParaTelaDeEstacionamentoVigente(usuario)
            }
        })
    }

    private fun vaiParaTelaDeEstacionamentoVigente(usuario: Usuario) {
        val intent = Intent(this, EstacionamentoVigenteActivity::class.java)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
    }

    class ClickHandler (val context: Context){
        fun estacionarClick() {
            val intent = Intent(context, EstacionarActivity::class.java)
            context.startActivity(intent)
        }
        fun creditosClick() {
            val intent = Intent(context, AdicionarCreditoActivity::class.java)
            context.startActivity(intent)
        }
    }
}
