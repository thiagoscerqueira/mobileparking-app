package br.com.enterprise.mobileparking.app.ui.apresentarEstacionamentoVigente

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.enterprise.mobileparking.R
import br.com.enterprise.mobileparking.app.ui.exibirOpcoesIniciais.OpcoesIniciaisActivity
import br.com.enterprise.mobileparking.app.util.DateTimeUtil
import br.com.enterprise.mobileparking.databinding.ActivityEstacionamentoVigenteBinding
import br.com.enterprise.mobileparking.domain.entity.Usuario
import org.koin.androidx.viewmodel.ext.android.viewModel

class EstacionamentoVigenteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEstacionamentoVigenteBinding

    private val viewModel by viewModel<BuscarEstacionamentoVigenteViewModel>()
    private val usuario = Usuario(id = "thiago", nome = "Thiago Silva Cerqueira")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscarEstacionamentoVigente()
        configurarBinding()
        observarEstacionamentoVigente()
        observarErro()
        observarFinalizarEstacionamento()
    }

    private fun buscarEstacionamentoVigente() {
        viewModel.buscarEstacionamentoVigente(usuario)
    }

    private fun configurarBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_estacionamento_vigente)
        binding.buscarEstacionamentoVigenteViewModel = viewModel
        binding.dateTimeUtil = DateTimeUtil
        binding.lifecycleOwner = this
    }

    private fun observarEstacionamentoVigente() {
        viewModel.estacionamentoVigente.observe(this, Observer {
            it?.let {
                if (it.tempoFinalizado()) {
                    vaiPraTelaInicial()
                } else {
                    viewModel.atualizaEstacionamentoVigente(it)}
                }
        })
    }

    private fun observarFinalizarEstacionamento() {
        viewModel.chamandoFinalizaEstacionamento.observe(this, Observer {
            it?.let { vaiPraTelaInicial() }
        })
    }

    private fun vaiPraTelaInicial() {
        Toast.makeText(this, "Estacionamento finalizado!", Toast.LENGTH_LONG).show()
        val intent = Intent(this, OpcoesIniciaisActivity::class.java)
        startActivity(intent)
    }

    private fun observarErro() {
        viewModel.erro.observe(this, Observer { it ->
            exibirToastErro()
        })
    }

    private fun exibirToastErro() {
        Toast.makeText(
            this, "Erro inesperado", Toast.LENGTH_LONG
        ).show()
    }
}
