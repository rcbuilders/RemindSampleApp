package com.remind.sampleapp.fragment_arguments

import android.content.Context
import android.content.Intent
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.activity_intent.IntentData
import com.remind.sampleapp.databinding.ActivityTestFragmentBinding

class ArgumentsExampleActivity :
    BaseActivity<ActivityTestFragmentBinding>(R.layout.activity_test_fragment) {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, ArgumentsExampleActivity::class.java)
        }
    }

    override fun initView() {
        super.initView()
        binding.apply {
            val newFragment = ArgumentsExampleFragment.newInstance(
                text = "김철수",
                number = 234,
                isOk = true,
                data = IntentData("김하나", 567)
            )
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, newFragment)
            transaction.commit()
        }
    }

}