package br.com.enterprise.mobileparking.app.ui

import androidx.lifecycle.ViewModel
import java.util.concurrent.ExecutorService

open class BackgroundViewModel(private val executor: ExecutorService) : ViewModel() {

    fun doInBackground(block: () -> Unit) { executor.submit(block) }

}
