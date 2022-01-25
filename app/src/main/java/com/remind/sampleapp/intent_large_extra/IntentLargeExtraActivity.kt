package com.remind.sampleapp.intent_large_extra

import android.content.Context
import android.content.Intent
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityIntentExampleBinding
import com.remind.sampleapp.utils.getLargeExtra
import com.remind.sampleapp.utils.putLargeExtra

class IntentLargeExtraActivity :
    BaseActivity<ActivityIntentExampleBinding>(R.layout.activity_intent_example) {

    companion object {
        private const val INTENT_EXTRA_KEY_NORMAL_DATA = "intent.extra.normal.data"
        private const val INTENT_EXTRA_KEY_LARGE_DATA = "intent.extra.large.data"

        fun createIntent(
            context: Context,
            normalData: String? = null,
            largeData: ByteArray? = null
        ) = Intent(context, IntentLargeExtraActivity::class.java).apply {
                putExtra(INTENT_EXTRA_KEY_NORMAL_DATA, normalData)
                putLargeExtra(INTENT_EXTRA_KEY_LARGE_DATA, largeData)
            }
    }

    override fun afterOnCreate() {
        super.afterOnCreate()

        val normalData: String? = intent.getStringExtra(INTENT_EXTRA_KEY_NORMAL_DATA)
        val largeData: ByteArray? = intent.getLargeExtra(INTENT_EXTRA_KEY_LARGE_DATA)
        println("normalData=$normalData, largeData=${largeData.contentToString()}")

        // null 체크하여 값 저장 코드 (위의 코드와 동일한 결과)
//        intent.getLargeExtra<ByteArray>(INTENT_EXTRA_KEY_LARGE_DATA)?.let {
//            println("largeData=${it.contentToString()}")
//        }
    }
}