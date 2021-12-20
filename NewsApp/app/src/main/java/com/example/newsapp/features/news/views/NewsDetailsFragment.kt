package com.example.newsapp.features.news.viewmodels.views

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapters.NewsDataAdapter
import com.example.newsapp.data.NewsItemData
import com.example.newsapp.databinding.DetailViewFragmentBinding
import com.example.newsapp.databinding.TopNewsFragmentBinding
import com.example.newsapp.features.news.viewmodels.TopNewsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {


    private var storyUrl: String? = null

    private lateinit var binding: DetailViewFragmentBinding

    companion object {
        val EXTRA_STORY_URL = "story_url"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DetailViewFragmentBinding.inflate(inflater, container, false)

        arguments?.let {
            storyUrl = it.getString(EXTRA_STORY_URL, "")
        }

        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.newsDetails.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.progress.isVisible = true
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.progress.isVisible = false
                super.onPageFinished(view, url)
            }
        }
        storyUrl?.let {
            binding.newsDetails.loadUrl(it)
        }

    }
}