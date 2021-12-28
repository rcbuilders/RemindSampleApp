package com.remind.sampleapp.base_fragment

import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityTestFragmentBinding

class TestFragmentActivity: BaseActivity<ActivityTestFragmentBinding>(R.layout.activity_test_fragment) {

    override fun initView() {
        super.initView()
        binding.apply {
            val newFragment = TestFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

}