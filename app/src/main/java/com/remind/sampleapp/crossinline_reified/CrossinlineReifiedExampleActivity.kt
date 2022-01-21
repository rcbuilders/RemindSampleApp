package com.remind.sampleapp.crossinline_reified

import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityInlineFuncExampleBinding

/**
 * crossinline reified example code
 * 결과는 Logcat으로 확인하세요.
 */
class CrossinlineReifiedExampleActivity:
    BaseActivity<ActivityInlineFuncExampleBinding>(R.layout.activity_crossinline_reified_example) {

    override fun afterOnCreate() {
        super.afterOnCreate()

        // crossinline
        inlineHello("김영희") {
            println(it)
        }

        // reified
        whoAreYou("코알라") {
            println(it)
        }
        whoAreYou(123) {
            println(it)
        }
        whoAreYou(123.45) {
            println(it)
        }
    }

    private inline fun <T> inlineHello(name: T, crossinline func: (String) -> Unit) {
        /**
         * 다른 고차함수에서 func를 호출시엔 crossinline 을 표시해주어야 함.
         */
        invokeFunc() {
            func("$name 반가워~^^")
        }
    }

    private fun invokeFunc(func: () -> Unit) {
        func.invoke()
    }

    private inline fun <reified T> whoAreYou(value: T, func: (String) -> Unit) {
        func(when(T::class.java) {
            String::class.java -> { "${value}는 글자 입니다." }
            Integer::class.java -> { "${value}는 숫자 입니다." }
            else -> { "${value}는 모르겠습니다." }
        })
    }

}