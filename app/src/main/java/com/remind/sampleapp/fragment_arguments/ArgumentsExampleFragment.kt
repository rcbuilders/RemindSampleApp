package com.remind.sampleapp.fragment_arguments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.remind.sampleapp.R
import com.remind.sampleapp.activity_intent.IntentData
import com.remind.sampleapp.base_fragment.BaseFragment
import com.remind.sampleapp.databinding.FragmentTestBinding
import java.io.Serializable

/**
 * Fragment arguments 를 쾌적하게 해보자.
 */
class ArgumentsExampleFragment : BaseFragment<FragmentTestBinding>(R.layout.fragment_test) {

    companion object {
        private const val INTENT_EXTRA_KEY_TEXT = "intent.extra.TEXT"
        private const val INTENT_EXTRA_KEY_NUMBER = "intent.extra.NUMBER"
        private const val INTENT_EXTRA_KEY_IS_OK = "intent.extra.is.OK"
        private const val INTENT_EXTRA_KEY_DATA = "intent.extra.DATA"

        fun newInstance(
            text: String? = null,
            number: Int? = null,
            isOk: Boolean? = null,
            data: IntentData? = null
        ) = ArgumentsExampleFragment().apply {
            arguments = Bundle().apply {
                text?.let { putString(INTENT_EXTRA_KEY_TEXT, it) }
                number?.let { putInt(INTENT_EXTRA_KEY_NUMBER, it) }
                isOk?.let { putBoolean(INTENT_EXTRA_KEY_IS_OK, it) }
                data?.let { putSerializable(INTENT_EXTRA_KEY_DATA, it) }
            }
        }
    }


    private val text: String? by argumentParams(INTENT_EXTRA_KEY_TEXT)
    private val number: Int? by argumentParams(INTENT_EXTRA_KEY_NUMBER, -1)
    private val isOk: Boolean? by argumentParams(INTENT_EXTRA_KEY_IS_OK, false)
    private val data: IntentData? by argumentParams(INTENT_EXTRA_KEY_DATA)

    override fun afterViewCreated() {
        super.afterViewCreated()

        /**
         * 일반적인 fragment arguments data get 방법
         */
        arguments?.getString(INTENT_EXTRA_KEY_TEXT)?.let {
            println("text is $it")
        }
        arguments?.getInt(INTENT_EXTRA_KEY_NUMBER)?.let {
            println("number is $it")
        }
        arguments?.getBoolean(INTENT_EXTRA_KEY_IS_OK)?.let {
            println("isOK is $it")
        }
        arguments?.getSerializable(INTENT_EXTRA_KEY_DATA)?.let {
            println("isOK is ${it as IntentData}")
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

inline fun<reified T: Any> Fragment.argumentParams(key: String, defaultValue: T) = lazy {
    val result = when(defaultValue) {
        is Boolean -> arguments?.getBoolean(key, defaultValue)
        is Int -> arguments?.getInt(key, defaultValue)
        is CharSequence -> arguments?.getString(key)
        is Serializable -> arguments?.getSerializable(key) ?: defaultValue
        else -> throw IllegalArgumentException("IllegalArgument value type [${defaultValue.javaClass}] / key [$key]")
    } as? T
    return@lazy result ?: defaultValue
}


inline fun<reified T: Any> Fragment.argumentParams(key: String, crossinline defaultValue: () -> T? = { null }) = lazy {
    val objectType = T::class.javaObjectType
    val result = when {
        Serializable::class.java.isAssignableFrom(objectType) -> arguments?.getSerializable(key) ?: defaultValue.invoke()
        else -> throw IllegalArgumentException("Illegal value type [${objectType}] / key [$key]")
    } as? T
    return@lazy result
}

inline fun<reified T: Any> Fragment.argumentParamsNotNull(key: String) = lazy<T> {
    val objectType = T::class.javaObjectType
    val result = when {
        Serializable::class.java.isAssignableFrom(objectType) -> arguments?.getSerializable(key)
        else -> throw IllegalArgumentException("Illegal value type [${objectType}] / key [$key]")
    } as T
    return@lazy result
}