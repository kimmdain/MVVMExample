package com.example.mvvmexample.model

import com.example.mvvmexample.model.enum.KakaoSearchSortEnum
import com.example.mvvmexample.model.response.ImageSearchResponse
import com.example.mvvmexample.model.service.KakaoSearchService
import io.reactivex.Single

class DataModelImpl(private val service:KakaoSearchService):DataModel{

    private val KAKAO_APP_KEY = "f19f1c2a2a86299194342dd1fe839655"

    override fun getData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int): Single<ImageSearchResponse> {
        return service.searchImage(auth = "KakaoAK $KAKAO_APP_KEY", query = query, sort = sort.sort, page = page, size = size)
    }
}