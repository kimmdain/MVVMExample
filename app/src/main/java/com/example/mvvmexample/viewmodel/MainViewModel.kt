package com.example.mvvmexample.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mvvmexample._base.BaseKotlinActivity
import com.example.mvvmexample._base.BaseKotlinViewModel
import com.example.mvvmexample.model.DataModel
import com.example.mvvmexample.model.enum.KakaoSearchSortEnum
import com.example.mvvmexample.model.service.KakaoSearchService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(private val model: DataModel): BaseKotlinViewModel() {

    private val TAG = "MainViewModel"

    fun getImageSearch(query: String, page:Int, size:Int) {
        addDisposable(model.getData(query, KakaoSearchSortEnum.Accuracy, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (documents.size > 0) {
                        _imageSearchResponseLiveData.postValue(this)
                    }
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            }))
    }


}