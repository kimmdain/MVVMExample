package com.example.mvvmexample.view

import android.os.Bundle
import com.example.mvvmexample.R
import com.example.mvvmexample._base.BaseKotlinActivity
import com.example.mvvmexample.viewmodel.MainViewModel

class MainActivity : BaseKotlinActivity< MainViewModel>() {

    override val layoutResourceId: Int
    get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {


    }

    override fun initDataBinding() {
    }


    override fun initAfterBinding() {

    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
}