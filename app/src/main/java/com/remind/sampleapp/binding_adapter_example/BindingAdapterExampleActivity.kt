package com.remind.sampleapp.binding_adapter_example

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityBindingAdapterExampleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

object BindingAdapterHelper {

    @BindingAdapter("profileUrl")
    @JvmStatic
    fun setProfileImage(view: ImageView, url: String?) {
        url?.let {
            Glide.with(view.context).load(it).circleCrop().into(view)
        }
    }

    @BindingAdapter("userName", "score")
    @JvmStatic
    fun setUserInfo(view: TextView, userName: String?, score: Int?) {
        score?.let {
            val grade = when {
                it >= 80 -> "A+"
                it >= 60 -> "A"
                it >= 40 -> "B+"
                it >= 20 -> "B"
                else -> "C"
            }
            view.text = "${userName}님의 등급은 ${grade}입니다."
        }
    }
}

class BindingAdapterExampleActivity :
    BaseActivity<ActivityBindingAdapterExampleBinding>(R.layout.activity_binding_adapter_example) {

    private val viewModel : BindingAdapterExampleViewModel by viewModel()

    override fun initViewModel() {
        super.initViewModel()
        binding.viewModel = viewModel
        binding.handler = DataBindingHandler()
        binding.lifecycleOwner = this
    }

    inner class DataBindingHandler {

        fun onHandlerClicked(view: View) {
            Toast.makeText(view.context, "handler를 이용한 click!!", Toast.LENGTH_SHORT).show()
        }

    }

}