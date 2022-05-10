package com.remind.sampleapp.activity_result

import android.content.Context
import android.content.Intent
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityChildABinding

class ChildAActivity : BaseActivity<ActivityChildABinding>(R.layout.activity_child_a) {

    companion object {

        const val INTENT_EXTRA_MSG = "intent.extra.msg"

        fun createIntent(context: Context, msg: String): Intent {
            return Intent(context, ChildAActivity::class.java).also {
                it.putExtra(INTENT_EXTRA_MSG, msg)
            }
        }
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        setResult(RESULT_OK, intent)
    }

}