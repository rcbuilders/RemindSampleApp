package com.remind.sampleapp.lorem_picsum.ui

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityLorempicsumListBinding
import com.remind.sampleapp.lorem_picsum.model.ImageInfo
import com.remind.sampleapp.lorem_picsum.ui.adapter.LoremPicsumListAdapter
import com.remind.sampleapp.lorem_picsum.ui.adapter.OnImageItemClickListener
import com.remind.sampleapp.lorem_picsum.viewmodel.LoremPicsumViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoremPicsumListActivity:
    BaseActivity<ActivityLorempicsumListBinding>(R.layout.activity_lorempicsum_list), OnImageItemClickListener<ImageInfo> {

    private val viewModel: LoremPicsumViewModel by viewModel()
    private val imageListAdapter: LoremPicsumListAdapter = LoremPicsumListAdapter(this)

    override fun initView() {
        super.initView()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        title = "CollapsingToolbar"

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(
                this@LoremPicsumListActivity,
                RecyclerView.VERTICAL,
                false)
            adapter = imageListAdapter
        }

        imageListAdapter.addLoadStateListener {
            if(it.append.endOfPaginationReached) {
                binding.emptyView.isVisible = imageListAdapter.itemCount == 0
            } else {
                binding.emptyView.isVisible = false
            }
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        binding.lifecycleOwner = this
    }

    override fun initListener() {
        super.initListener()
        binding.apply {
            swipeLayout.setOnRefreshListener { imageListAdapter.refresh() }
        }

        lifecycleScope.launchWhenCreated {
            imageListAdapter.loadStateFlow.distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect {
                    binding.swipeLayout.isRefreshing = false
                }
        }
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        lifecycleScope.launchWhenStarted {
            viewModel.fetchImageList().collect {
                imageListAdapter.submitData(it)
            }
        }
    }

    override fun onImageItemClicked(item: ImageInfo?) {
        item?.id?.let { imageId ->
            LoremPicsumDetailActivity.createIntent(this, imageId).also {
                startActivity(it)
            }
        }
    }
}