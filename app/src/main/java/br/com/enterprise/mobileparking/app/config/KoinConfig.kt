package br.com.enterprise.mobileparking.app.config

import br.com.enterprise.mobileparking.app.ui.adicionarCredito.AdicionarCreditoViewModel
import br.com.enterprise.mobileparking.app.ui.apresentarEstacionamentoVigente.BuscarEstacionamentoVigenteViewModel
import br.com.enterprise.mobileparking.app.ui.estacionar.EstacionarViewModel
import br.com.enterprise.mobileparking.app.ui.exibirOpcoesIniciais.BuscarDadosUsuarioViewModel
import br.com.enterprise.mobileparking.domain.repository.CreditoRepository
import br.com.enterprise.mobileparking.domain.repository.EstacionamentoRepository
import br.com.enterprise.mobileparking.domain.repository.TempoPermanenciaRepository
import br.com.enterprise.mobileparking.domain.repository.UsuarioRepository
import br.com.enterprise.mobileparking.resources.network.CreditoApi
import br.com.enterprise.mobileparking.resources.network.EstacionamentoApi
import br.com.enterprise.mobileparking.resources.network.TempoPermanenciaApi
import br.com.enterprise.mobileparking.resources.network.fuel.CreditoFuelApi
import br.com.enterprise.mobileparking.resources.network.fuel.EstacionamentoFuelApi
import br.com.enterprise.mobileparking.resources.network.fuel.TempoPermanenciaFuelApi
import br.com.enterprise.mobileparking.resources.repository.CreditoRepositoryImpl
import br.com.enterprise.mobileparking.resources.repository.EstacionamentoRepositoryImpl
import br.com.enterprise.mobileparking.resources.repository.TempoPermanenciaRepositoryImpl
import br.com.enterprise.mobileparking.resources.repository.UsuarioRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single<EstacionamentoApi> { EstacionamentoFuelApi() }
    single<CreditoApi> { CreditoFuelApi() }
    single<TempoPermanenciaApi> { TempoPermanenciaFuelApi() }
}

val repositoryModule = module {
    single<EstacionamentoRepository> { EstacionamentoRepositoryImpl(get()) }
    single<UsuarioRepository> { UsuarioRepositoryImpl(get()) }
    single<TempoPermanenciaRepository> { TempoPermanenciaRepositoryImpl(get()) }
    single<CreditoRepository> { CreditoRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { BuscarEstacionamentoVigenteViewModel(repository = get()) }
    viewModel { BuscarDadosUsuarioViewModel(repository = get()) }
    viewModel { EstacionarViewModel(repository = get(), estacionamentoRepository = get()) }
    viewModel { AdicionarCreditoViewModel(repository = get()) }
}