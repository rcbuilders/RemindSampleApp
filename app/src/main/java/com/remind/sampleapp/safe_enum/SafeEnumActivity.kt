package com.remind.sampleapp.safe_enum

import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityIntentExampleBinding

enum class Fruit {
    APPLE,
    ORANGE,
    STRAWBERRY,
    GRAPE
}

// 특정 파라미터를 저장할 수 있습니다.
enum class Animal(val displayName: String) {
    DOG("개"),
    CAT("고양이"),
    RABBIT("토끼"),
    PIG("돼지")
}

/**
 * 모든 Enum 타입을 안전하게 반환합니다.
 */
inline fun <reified T : Enum<T>> safeValueOf(type: String, default: T): T {
    return try {
        java.lang.Enum.valueOf(T::class.java, type)
    } catch (e: IllegalArgumentException) {
        default
    }
}

class SafeEnumActivity :
    BaseActivity<ActivityIntentExampleBinding>(R.layout.activity_intent_example) {

    override fun afterOnCreate() {
        super.afterOnCreate()

        println("내가 좋아하는 과일은 ${Fruit.valueOf("APPLE").name} 입니다.")

        // IllegalArgumentException: No enum constant com.remind.sampleapp.safe_enum.Fruit.MELON 에러 발생 예외처리함.
        println(
            "내가 좋아하는 과일은 ${
                try {
                    Fruit.valueOf("MELON").name
                } catch (e: IllegalArgumentException) {
                    Fruit.GRAPE
                }
            } 입니다."
        )

        // MELON 이 없을 경우엔 default 값을 표시하도록 함.
        println("내가 좋아하는 과일은 ${safeValueOf("MELON", Fruit.GRAPE).name} 입니다.")

        // 모든 Enum 타입에 사용이 가능합니다.
        println("내가 좋아하는 동물은 ${safeValueOf("COW", Animal.RABBIT).displayName}입니다.")
    }

}