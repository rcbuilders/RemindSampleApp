package com.remind.sampleapp.lorem_picsum.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.Constants
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityLorempicsumDetailBinding
import com.remind.sampleapp.lorem_picsum.viewmodel.LoremPicsumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoremPicsumDetailActivity:
    BaseActivity<ActivityLorempicsumDetailBinding>(R.layout.activity_lorempicsum_detail) {

    companion object {
        fun createIntent(context: Context, imageId: String): Intent {
            return Intent(context, LoremPicsumDetailActivity::class.java).also {
                it.putExtra(Constants.KEY_EXTRA_IMAGE_ID, imageId)
            }
        }
    }

    private val viewModel: LoremPicsumViewModel by viewModel()

    override fun initViewModel() {
        super.initViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun afterOnCreate() {
        super.afterOnCreate()

        lifecycleScope.launchWhenStarted {
            intent?.getStringExtra(Constants.KEY_EXTRA_IMAGE_ID)?.let {
                viewModel.getImageInfo(it)
            }
        }
    }

}

@BindingAdapter("url")
fun setImageViewUrl(view: AppCompatImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(it).into(view)
    }
}