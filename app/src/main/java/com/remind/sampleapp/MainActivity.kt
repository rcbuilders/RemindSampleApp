package com.remind.sampleapp

import android.content.Intent
import com.remind.sampleapp.base_fragment.TestFragmentActivity
import com.remind.sampleapp.databinding.ActivityMainBinding
import com.remind.sampleapp.lorem_picsum.ui.LoremPicsumDetailActivity
import com.remind.sampleapp.lorem_picsum.ui.LoremPicsumListActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
        super.initView()
        binding.apply {
            button.setOnClickListener {
                Intent(this@MainActivity, TestFragmentActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnLorempicsum.setOnClickListener {
                Intent(this@MainActivity, LoremPicsumListActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

}