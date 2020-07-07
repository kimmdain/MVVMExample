package com.example.mvvmexample.di

import com.example.mvvmexample.model.DataModelImpl
import com.example.mvvmexample.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel // 이걸로 해야함
import org.koin.dsl.module.module

var modelPart = module {
    factory {
        DataModelImpl()
    }
}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
}

var myDiModule = listOf(modelPart, viewModelPart)