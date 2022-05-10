package com.remind.sampleapp.activity_result

import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.activity_result.ChildAActivity.Companion.INTENT_EXTRA_MSG
import com.remind.sampleapp.databinding.ActivityParentBinding

class ParentActivity : BaseActivity<ActivityParentBinding>(R.layout.activity_parent) {

    override fun initListener() {
        super.initListener()
        binding.run {
            btnLaunchA.setOnClickListener {
                /**
                 * startActivityForResult() 를 대신해서 callback을 이용하여 activity를 호출합니다.
                 */
                ChildAActivity.createIntent(this@ParentActivity, "A Child Callback OK!!").also {
                    childForResult.launch(it)
                }
            }
            btnLaunchB.setOnClickListener {
                /**
                 * Deprecated 된 startActivityForResult() 사용 코드입니다.
                 */
                ChildAActivity.createIntent(this@ParentActivity, "A Child onActivityResult OK!!")
                    .also {
                        startActivityForResult(it, 1)
                    }
            }
        }
    }

    private val childForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    showMessage(result.data?.getStringExtra(INTENT_EXTRA_MSG))
                }
            }
        }

    /**
     * onActivityResult() 함수가 Deprecated 되었습니다.
     * 이를 대신해서 위의 childForResult Callback 을 사용합니다.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_OK -> {
                showMessage(data?.getStringExtra(INTENT_EXTRA_MSG))
            }
        }
    }

    private fun showMessage(msg: String?) =
        Toast.makeText(this, "result Msg : $msg", Toast.LENGTH_SHORT).show()

}