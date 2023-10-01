package lab_8

import kotlin.math.abs
import kotlin.math.pow

//№8 Поразрядная

fun radixSort(array: Array<Int>) {
//    TODO проход для отрицательных чисел
    var maxNumberLen = 0
    for (number in array) {
        if (number.toString().length > maxNumberLen) {
            maxNumberLen = abs(number).toString().length
        }
    }

    var basesSizes: Array<Int> = Array(10) { 0 }
    var bases: Array<Array<Int?>> = Array(10) { Array(10) { null } }
    for (i in 0..<maxNumberLen) {
        for (number in array) {
            val currentBase = abs((number / 10.0.pow(i).toInt()) % (10))
            bases[currentBase][basesSizes[currentBase]] = number
            basesSizes[currentBase]++
        }
        var count = 0
        for (tempArray in bases){
            for (number in tempArray){
                if (number != null){
                    array[count] = number
                    count++
                }
            }
        }
        basesSizes = Array(10) { 0 }
        bases = Array(10) { Array(10) { null } }
    }
}

fun main() {
    val sortingArray = arrayOf(1, 23, 643, 2, 67838)
    radixSort(sortingArray)
    for (number in sortingArray){
        println(number)
    }
}