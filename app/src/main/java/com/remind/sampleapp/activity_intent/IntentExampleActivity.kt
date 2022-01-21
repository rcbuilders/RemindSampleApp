package com.remind.sampleapp.activity_intent

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityIntentExampleBinding
import java.io.Serializable

data class IntentData(
    val name: String? = null,
    val num: Int? = null
) : Serializable

class IntentExampleActivity :
    BaseActivity<ActivityIntentExampleBinding>(R.layout.activity_intent_example) {

    companion object {
        private const val INTENT_EXTRA_KEY_TEXT = "intent.extra.TEXT"
        private const val INTENT_EXTRA_KEY_NUMBER = "intent.extra.NUMBER"
        private const val INTENT_EXTRA_KEY_IS_OK = "intent.extra.is.OK"
        private const val INTENT_EXTRA_KEY_DATA = "intent.extra.DATA"

        fun createIntent(
            context: Context,
            text: String? = null,
            number: Int? = null,
            isOk: Boolean? = null,
            data: IntentData? = null
        ): Intent {
            return Intent(context, IntentExampleActivity::class.java).apply {
                text?.let { putExtra(INTENT_EXTRA_KEY_TEXT, it) }
                number?.let { putExtra(INTENT_EXTRA_KEY_NUMBER, it) }
                isOk?.let { putExtra(INTENT_EXTRA_KEY_IS_OK, it) }
                data?.let { putExtra(INTENT_EXTRA_KEY_DATA, it as Serializable) }
            }
        }
    }


    private val text: String? by extraParams(INTENT_EXTRA_KEY_TEXT)
    private val number: Int? by extraParams(INTENT_EXTRA_KEY_NUMBER, -1)
    private val isOk: Boolean? by extraParams(INTENT_EXTRA_KEY_IS_OK, false)
    private val data: IntentData? by extraParams(INTENT_EXTRA_KEY_DATA)

    override fun afterOnCreate() {
        super.afterOnCreate()

        /**
         * 일반적인 intent data get 방법
         */
        intent?.getStringExtra(INTENT_EXTRA_KEY_TEXT)?.let {
            println("text is $it")
        }
        intent?.getIntExtra(INTENT_EXTRA_KEY_NUMBER, -1)?.let {
            println("number is $it")
        }
        intent?.getBooleanExtra(INTENT_EXTRA_KEY_IS_OK, false)?.let {
            println("isOk is $it")
        }
        intent?.getSerializableExtra(INTENT_EXTRA_KEY_DATA)?.let {
            println("isOk is ${it as IntentData}")
        }

        println("-------------------------")

        /**
         * extension 을 이용한 방법
         */
        println("text is $text")
        println("number is $number")
        println("isOk is $isOk")
        println("data is $data")
    }

}

/**
 * private val a: Int by extraParams('key', -1)
 */
inline fun <reified T : Any> Activity.extraParams(key: String, defaultValue: T) = lazy {
    val result = when (defaultValue) {
        is Boolean -> intent.getBooleanExtra(key, defaultValue)
        is Int -> intent.getIntExtra(key, defaultValue)
        is CharSequence -> intent.getStringExtra(key)
        is Serializable -> intent.getSerializableExtra(key)
        else -> throw IllegalArgumentException("IllegalArgument value type [${defaultValue.javaClass}] / key [$key]")
    } as? T
    return@lazy result ?: defaultValue
}


/**
 * private val a: Serializable by extraParams('key') { as Serializable() }
 */
inline fun <reified T : Any> Activity.extraParams(
    key: String,
    crossinline defaultValue: () -> T? = { null }
) = lazy {
    val objectType = T::class.javaObjectType
    val result = when {
        Serializable::class.java.isAssignableFrom(objectType) -> intent.getSerializableExtra(key)
            ?: defaultValue.invoke()
        else -> throw IllegalArgumentException("Illegal value type [${objectType}] / key [$key]")
    } as? T
    return@lazy result
}

/**
 * private val a: Serializable by extraParams('key') // Not null
 */
inline fun <reified T : Any> Activity.extraParamsNotNull(key: String) = lazy<T> {
    val objectType = T::class.javaObjectType
    val result = when {
        Serializable::class.java.isAssignableFrom(objectType) -> intent.getSerializableExtra(key)
        else -> throw IllegalArgumentException("Illegal value type [${objectType}] / key [$key]")
    } as T
    return@lazy result
}