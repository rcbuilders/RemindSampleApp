package com.remind.sampleapp.generics_example

import com.remind.sampleapp.BaseActivity
import com.remind.sampleapp.R
import com.remind.sampleapp.databinding.ActivityGenericsExampleBinding

/**
 * 제네릭 타입 설명을 위한 Activity 입니다.
 */
class GenericsExampleActivity:
    BaseActivity<ActivityGenericsExampleBinding>(R.layout.activity_generics_example) {

    override fun afterOnCreate() {
        super.afterOnCreate()

        /**
         * 동일한 동작을 하는 클래스에 입력 파라미터 타입이 다르다면, 타입별로 클래스를 만들어야 함.
         */
        IntegerPrinter(123).runPrint()
        StringPrinter("Re:Mind").runPrint()
        /**
         * 제네릭 타입으로 클래스를 만들면, 입력 파라미터 타입이 다른더라도 하나의 클래스로 사용이 가능함.
         */
        EveryPrinter<Int>(123).runPrint()
        EveryPrinter<String>("Re:Mind").runPrint()

        /**
         * 타입 제한을 위한 코드
         */
        AnimalPrinter(Dog("바둑이", 3)).runPrint()
        AnimalPrinter(Cat("나비", 5)).runPrint()

        /**
         * 여러개의 재네릭 타입을 사용할 수 있음.
         * (함수, 클래스, 인터페이스 모두 가능)
         */
        printHello("바둑이", 3)
        printHello("나비", "다섯")

        /**
         * 제네릭 파라미터 유형을 모르는 경우 <스타 프로젝션>
         */
        val animals = listOf<Animal>(
            Dog("바둑이", 3),
            Cat("나비", 5)
        )
        printList(animals)
        printList(listOf<Int>(123, 456))
    }

    private fun <T1, T2> printHello(name: T1, age: T2) {
        println("${name}는 ${age}살 입니다.")
    }

    private fun printList(list: List<*>) {
        list.map { println(it.toString()) }
    }

}

/**
 * 타입 제한 (Animal 클래스 타입만 가능)
 */
class AnimalPrinter<T : Animal>(private val printAnimal: T) {
    fun runPrint() {
        println(printAnimal.toString())
    }
}

class Dog(name: String, age: Int): Animal(name, age)
class Cat(name: String, age: Int): Animal(name, age)

open class Animal(private val name: String, private val age: Int) {
    override fun toString(): String {
        return "${name}는 ${age}살 입니다."
    }
}

class EveryPrinter<T>(private val printValue: T) {
    fun runPrint() {
        println("$printValue")
    }
}

class IntegerPrinter(private val printValue: Int) {
    fun runPrint() {
        println("$printValue")
    }
}

class StringPrinter(private val printValue: String) {
    fun runPrint() {
        println(printValue)
    }
}