package com.remind.sampleapp.lorem_picsum.ui

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityLorempicsumListBinding
import com.remind.sampleapp.lorem_picsum.ui.adapter.LoremPicsumListAdapter
import com.remind.sampleapp.lorem_picsum.viewmodel.LoremPicsumViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoremPicsumListActivity:
    BaseActivity<ActivityLorempicsumListBinding>(R.layout.activity_lorempicsum_list) {

    private val viewModel: LoremPicsumViewModel by viewModel()
    private val imageListAdapter: LoremPicsumListAdapter = LoremPicsumListAdapter()

    override fun initView() {
        super.initView()
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(
                this@LoremPicsumListActivity,
                RecyclerView.VERTICAL,
                false)
            adapter = imageListAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        binding.lifecycleOwner = this
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        lifecycleScope.launchWhenStarted {
            viewModel.fetchImageList().collect {
                imageListAdapter.submitData(it)
            }
        }
    }
}