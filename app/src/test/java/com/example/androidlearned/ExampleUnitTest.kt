package com.example.androidlearned

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
       val one = Human("张三",19,"男" )
        println("one: $one,")
        var sort = 1
        sort = 2

        for (i  in 1..4) {
            println(i)
        }

        val list = listOf("item1", "item2", "item3", "item4", "item5","fragment1")

        for (item in list) {
            println(item)
        }
        for (index in list.indices) {
            println(index)
        }

        var index = 0
        while (index < list.size) {
            println("索引:$index 值: ${list.get(index)}")
            index++
        }
        var enter = "哈哈"
        var res:String?
        when(enter) {
            "哈哈" -> res = "when1"
            "hh" -> res = "when2"
            else -> res = "when3"
        }
        println(res)

        var set = setOf("apple", "banana", "kiwifruit")
        when{
            "apple" in set -> println("apple")
            "banana" in set -> println("apple")
            "kiwifruit" in set -> println("apple")
        }

         val newList = list.filter{
            it.contains("item")
        }.sortedBy {
            it.length
         }.map {
            it.plus("哈哈")
        }
        println(newList)
    }
}


public class Human(val name: String, val age: Number, sex: String) {
    val sex: String = sex
     var identify: String

    init {
        identify = "1"
    }
    override fun toString(): String {
        return "Human(name='$name', age=$age, sex='$sex')"
    }

}

