package com.example.androidlearned.viewModal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.cachedIn
import com.example.androidlearned.MyApplication
import com.example.androidlearned.repository.ExampleRepository

class ExampleViewModel (repository: ExampleRepository) : ViewModel() {

//    private val exampleRepository = ExampleRepository(MokeAPi.create()) // 创建一个 ExampleRepository 实例，不依靠外部传入，省事可以这样做
    val examplePagingDataFlow = repository.getExamplePagingData().cachedIn(viewModelScope)

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY]) as MyApplication
                // 创建一个 ExampleViewModel 实例，并传入 ExampleRepository 实例，这是最佳方式，当然最好的是用hilt库
                return ExampleViewModel(
                    application.getExampleRepository()
                ) as T
            }
        }
    }
}