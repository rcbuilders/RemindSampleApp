package com.remind.sampleapp

import android.content.Intent
import com.remind.sampleapp.activity_intent.IntentData
import com.remind.sampleapp.activity_intent.IntentExampleActivity
import com.remind.sampleapp.base_fragment.TestFragmentActivity
import com.remind.sampleapp.binding_adapter_example.BindingAdapterExampleActivity
import com.remind.sampleapp.crossinline_reified.CrossinlineReifiedExampleActivity
import com.remind.sampleapp.databinding.ActivityMainBinding
import com.remind.sampleapp.date_format.DateFormatActivity
import com.remind.sampleapp.fragment_arguments.ArgumentsExampleActivity
import com.remind.sampleapp.generics_example.GenericsExampleActivity
import com.remind.sampleapp.inline_func.InlineFuncExampleActivity
import com.remind.sampleapp.intent_large_extra.IntentLargeExtraActivity
import com.remind.sampleapp.lorem_picsum.ui.LoremPicsumListActivity
import com.remind.sampleapp.safe_enum.SafeEnumActivity
import com.remind.sampleapp.swipe_finish_example.SwipeFinishExampleActivity

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
            btnGenericsExample.setOnClickListener {
                Intent(this@MainActivity, GenericsExampleActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnInlineFuncExample.setOnClickListener {
                Intent(this@MainActivity, InlineFuncExampleActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnCrossinlineReifiedExample.setOnClickListener {
                Intent(this@MainActivity, CrossinlineReifiedExampleActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnIntentExample.setOnClickListener {
                IntentExampleActivity.createIntent(
                    this@MainActivity,
                    text = "바둑이",
                    number = 123,
                    isOk = true,
                    data = IntentData("김미미", 456)
                ).also {
                    startActivity(it)
                }
            }
            btnFragmentArgumentsExample.setOnClickListener {
                ArgumentsExampleActivity.createIntent(this@MainActivity).also {
                    startActivity(it)
                }
            }
            btnSafeEnumExample.setOnClickListener {
                Intent(this@MainActivity, SafeEnumActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnIntentLargeExtraExample.setOnClickListener {
                /**
                 * TransactionTooLargeException이 발생함.
                 */
//                Intent(this@MainActivity, IntentLargeExtraActivity::class.java).also {
//                    // ByteArray 사이즈가 커서 TransactionTooLargeException이 발생함.
//                    it.putExtra("intent.extra.large.data", ByteArray(1000000))
//                    startActivity(it)
//                }

                /**
                 * createIntent()함수에서 putLargeExtra()로 데이터를 추가하여 Exception이 발생하지 않음.
                 */
                IntentLargeExtraActivity.createIntent(
                    this@MainActivity,
                    "알반적인 텍스트",
                    ByteArray(1000000)
                ).also {
                    startActivity(it)
                }
            }
            btnBindingAdapterExample.setOnClickListener {
                Intent(this@MainActivity, BindingAdapterExampleActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnSwipeFinishExample.setOnClickListener {
                Intent(this@MainActivity, SwipeFinishExampleActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnDateFormatExample.setOnClickListener {
                Intent(this@MainActivity, DateFormatActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

}