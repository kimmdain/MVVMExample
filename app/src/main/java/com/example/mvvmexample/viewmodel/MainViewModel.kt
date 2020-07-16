package com.example.mvvmexample.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmexample._base.BaseKotlinActivity
import com.example.mvvmexample._base.BaseKotlinViewModel
import com.example.mvvmexample.model.DataModel
import com.example.mvvmexample.model.enum.KakaoSearchSortEnum
import com.example.mvvmexample.model.response.ImageSearchResponse
import com.example.mvvmexample.model.service.KakaoSearchService
import com.example.mvvmexample.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainViewModel(private val model: DataModel): BaseKotlinViewModel() {

    private val TAG = "MainViewModel"

    private val _imageSearchResponseLiveData = MutableLiveData<ImageSearchResponse>()
    val imageSearchResponseLiveData: LiveData<ImageSearchResponse> get() = _imageSearchResponseLiveData

    fun getImageSearch(query: String, page:Int, size:Int) {
        addDisposable(model.getData(query, KakaoSearchSortEnum.Accuracy, page, size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    if (documents.size > 0) {
                        _imageSearchResponseLiveData.postValue(this)
                    }
                    Log.d("TAG", "document : ${it.documents}")
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            }))

    }

    private val _startSubActivityEvent = SingleLiveEvent<Any>()
    val startSubActivityEvent:LiveData<Any>
        get() = _startSubActivityEvent

    fun doSomething(){
        _startSubActivityEvent.call()
    }


}