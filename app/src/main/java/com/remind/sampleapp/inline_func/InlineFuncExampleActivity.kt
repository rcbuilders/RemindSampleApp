package com.remind.sampleapp.inline_func

import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityInlineFuncExampleBinding

class InlineFuncExampleActivity :
    BaseActivity<ActivityInlineFuncExampleBinding>(R.layout.activity_inline_func_example) {

    override fun afterOnCreate() {
        super.afterOnCreate()

        println(getHello("김철수"))
        inlineHello("김영희") {
            println(it)
        }
        inlineHello2("김미미") {
            println(it)
        }
        val value = 30
        20.isOver(value) { isOver ->
            if(isOver)
                println("20은 ${value}보다 크다")
            else
                println("20은 ${value}보다 작다")
        }

        var name: String? = "바둑이"
        name.notNull {
            println("나의 이름은 $this 입니다.")
        }
    }

    private fun <T> getHello(name: T): String {
        return "$name 반가워~^^"
    }

    private inline fun <T> inlineHello(name: T, func: (String) -> Unit) {
        func("$name 반가워~^^")
    }

    private inline fun <T> inlineHello2(name: T, noinline func: (String) -> Unit) {
        getHello2(name, func)
    }

    private fun <T> getHello2(name: T, func: (String) -> Unit) {
        func("$name 반가워~^^")
    }

    private inline fun Int.isOver(value: Int, func: (Boolean) -> Unit) {
        func(this > value)
    }

    private inline fun String?.notNull(body: String.() -> Unit) {
        this?.body()
    }
}