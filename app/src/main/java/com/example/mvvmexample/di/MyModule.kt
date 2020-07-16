package com.example.mvvmexample.di

import com.example.mvvmexample.MainSearchRecyclerViewAdapter
import com.example.mvvmexample.model.DataModel
import com.example.mvvmexample.model.DataModelImpl
import com.example.mvvmexample.model.service.KakaoSearchService
import com.example.mvvmexample.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel // 이걸로 해야함
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitPart = module {
    single<KakaoSearchService> {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoSearchService::class.java)
    }
}

var adapterPart = module {
    factory {
        MainSearchRecyclerViewAdapter()
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)