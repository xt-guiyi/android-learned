package com.example.androidlearned.viewModal

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.cachedIn
import com.example.androidlearned.repository.ExampleRepository

class ExampleViewModel (repository: ExampleRepository) : ViewModel() {
    val ExamplePagingDataFlow = repository.getExamplePagingData().cachedIn(viewModelScope)

    companion object {

//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras
//            ): T {
//                // Get the Application object from extras
//                val application = checkNotNull(extras[APPLICATION_KEY])
//
//                return ExampleViewModel(
//                ) as T
//            }
//        }
    }
}