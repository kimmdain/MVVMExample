package com.example.mvvmexample.model

import com.example.mvvmexample.model.enum.KakaoSearchSortEnum
import com.example.mvvmexample.model.response.ImageSearchResponse
import io.reactivex.Single

interface DataModel {
    fun getData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int): Single<ImageSearchResponse>
}
