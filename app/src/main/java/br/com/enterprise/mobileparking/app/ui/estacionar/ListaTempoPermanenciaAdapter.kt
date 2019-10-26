package br.com.enterprise.mobileparking.app.ui.estacionar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.enterprise.mobileparking.R
import br.com.enterprise.mobileparking.app.util.MoedaUtil
import br.com.enterprise.mobileparking.databinding.TempoPermanenciaItemBinding
import br.com.enterprise.mobileparking.domain.entity.TempoPermanencia

class ListaTempoPermanenciaAdapter(
    private val onItemClick: (Int) -> Unit = { }
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val temposPermanencia = mutableListOf<TempoPermanencia>()

    fun atualizarTemposPermanencia(temposPermanencia: List<TempoPermanencia>) {
        this.temposPermanencia.clear()

        this.temposPermanencia.addAll(temposPermanencia)

        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListaTemposPermanencia(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.tempo_permanencia_item, parent, false))
    }

    override fun getItemCount() = temposPermanencia.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tempoPermanencia = temposPermanencia[position]

        val binding = (holder as ListaTemposPermanencia).binding

        binding.tempoPermanencia = tempoPermanencia
        binding.moedaUtil = MoedaUtil
        binding.layout.setOnClickListener{ onItemClick(tempoPermanencia.tempo) }
    }

}

private class ListaTemposPermanencia(view: View) : RecyclerView.ViewHolder(view) {
    val binding: TempoPermanenciaItemBinding = TempoPermanenciaItemBinding.bind(view)

}
