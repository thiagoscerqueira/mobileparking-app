package br.com.enterprise.mobileparking.app

import android.app.Application
import br.com.enterprise.mobileparking.BuildConfig.API_URL
import br.com.enterprise.mobileparking.app.config.networkModule
import br.com.enterprise.mobileparking.app.config.repositoryModule
import br.com.enterprise.mobileparking.app.config.viewModelModule
import com.github.kittinunf.fuel.core.FuelManager
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MobileParkingApp : Application() {

    override fun onCreate() {
        super.onCreate()

        FuelManager.instance.basePath = API_URL

        startKoin {
            androidLogger()

            androidContext(this@MobileParkingApp)

            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}
