package com.remind.sampleapp.sorted_with

import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivitySortedWithExampleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SortedWithExampleActivity :
    BaseActivity<ActivitySortedWithExampleBinding>(R.layout.activity_sorted_with_example) {

    private val viewModel: SortedWithExampleViewModel by viewModel()

    override fun initView() {
        super.initView()
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(
                this@SortedWithExampleActivity,
                RecyclerView.VERTICAL,
                false)
            adapter = SortedWithListAdapter()
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        lifecycleScope.launchWhenStarted {
            viewModel.fetchUserScoreList()
        }
    }

}

@BindingAdapter("items")
fun setListItems(view: RecyclerView, items: List<UserScore>?) {
    (view.adapter as SortedWithListAdapter).submitList(items)
}