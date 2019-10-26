package br.com.enterprise.mobileparking.app.ui.adicionarCredito

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.enterprise.mobileparking.R
import br.com.enterprise.mobileparking.app.ui.exibirOpcoesIniciais.OpcoesIniciaisActivity
import br.com.enterprise.mobileparking.databinding.ActivityCreditoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdicionarCreditoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreditoBinding
    private val viewModel by viewModel<AdicionarCreditoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configurarBinding()
        observarAdicionarCreditos()
    }

    private fun configurarBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_credito)
        binding.adicionarCreditoViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun observarAdicionarCreditos() {
        viewModel.adicionaCreditos.observe(this, Observer {
            vaiPraTelaInicial()
        })
    }

    private fun vaiPraTelaInicial() {
        Toast.makeText(this, "Recarga finalizada com sucesso!", Toast.LENGTH_LONG).show()
        val intent = Intent(this, OpcoesIniciaisActivity::class.java)
        startActivity(intent)
    }

}
