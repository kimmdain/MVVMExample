package com.example.mvvmexample.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mvvmexample.MainSearchRecyclerViewAdapter
import com.example.mvvmexample.R
import com.example.mvvmexample._base.BaseKotlinActivity
import com.example.mvvmexample.databinding.ActivityMainBinding
import com.example.mvvmexample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    private val mainSearchRecyclerViewAdapter: MainSearchRecyclerViewAdapter by inject()

    override fun initStartView() {
        main_activity_search_recycler_view.run {
            adapter = mainSearchRecyclerViewAdapter
            layoutManager = StaggeredGridLayoutManager(3, 1).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                orientation = StaggeredGridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.imageSearchResponseLiveData.observe(this, Observer {
            mainSearchRecyclerViewAdapter.clearItem()
            it.documents.forEach {document ->
                mainSearchRecyclerViewAdapter.addImageItem(document.image_url, document.doc_url)
            }
            mainSearchRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        main_activity_search_button.setOnClickListener {
            viewModel.getImageSearch(main_activity_search_text_view.text.toString(), 1, 80)
        }
    }

//    override fun initDataBinding(){
//        viewModel.startSubActivityEvent.observe(this, Observe{
//            startActivity(Intent(applicationContext, SubActivity::class.java))
//        })
//    }
//
//    override fun initAfterBinding(){
//        do_something_button.setOnClickListener{
//            viewModel.doSomething()
//        }
//    }




}