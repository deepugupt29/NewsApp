package com.example.newsapp.features.news.viewmodels.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapters.NewsDataAdapter
import com.example.newsapp.data.NewsItemData
import com.example.newsapp.databinding.TopNewsFragmentBinding
import com.example.newsapp.features.news.viewmodels.TopNewsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopNewsFragment : Fragment() {

    private val mViewModel: TopNewsFragmentViewModel by viewModels()

    private var newsItemsList =  ArrayList<NewsItemData>()

    private var newsIdsList =  ArrayList<Int>()

    private lateinit var newsDataAdapter: NewsDataAdapter

    private lateinit var binding: TopNewsFragmentBinding

    private var loadmore = true
    
    private var startIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = TopNewsFragmentBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsDataAdapter = NewsDataAdapter(activity!!.supportFragmentManager, newsItemsList)

        binding.recyclerView.apply {
            adapter = newsDataAdapter
            layoutManager = LinearLayoutManager(activity)
        }.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            //TODO Change this and make use of pagination 
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastVisiblePosition: Int = getLastVisiblePosition(recyclerView)
                if (lastVisiblePosition == recyclerView.childCount) {
                    if (loadmore) {
                        loadmore =false;
                        var lastIndex = 0;

                        if(startIndex + 6 > newsIdsList.size ) {
                            lastIndex = newsIdsList.size - startIndex
                        } else {
                            lastIndex = startIndex + 6
                        }

                        val subList = newsIdsList.subList(startIndex, lastIndex)

                        startIndex = lastIndex + 1;
                        
                        for (item in subList) {
                            mViewModel.getNewsItemsDetails(item)
                        }
                        loadmore = true;
                    }
                }
            }


        })

        mViewModel.getTopStories();

        mViewModel.newsIds.observe(viewLifecycleOwner, Observer { items ->
            newsIdsList = ArrayList(items)

            var lastIndex = 0;

            if(startIndex + 6 > newsIdsList.size ) {
                lastIndex = newsIdsList.size - startIndex
            } else {
                lastIndex = startIndex + 6
            }

            val subList = newsIdsList.subList(startIndex, lastIndex)

            startIndex = lastIndex + 1;
            
            for (item in subList) {
                mViewModel.getNewsItemsDetails(item)
            }
        })

        mViewModel.newsItemDetails.observe(viewLifecycleOwner, Observer {
            newsItemsList.add(it)
            newsDataAdapter.notifyDataSetChanged()
        })



    }

    fun getLastVisiblePosition(rv: RecyclerView?): Int {
        if (rv != null) {
            val layoutManager = rv
                .layoutManager
            if (layoutManager is LinearLayoutManager) {
                return layoutManager.findLastVisibleItemPosition()
            }
        }
        return 0
    }
}