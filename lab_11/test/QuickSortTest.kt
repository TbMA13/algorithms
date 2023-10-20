package test

import org.junit.jupiter.api.Assertions.*

class QuickSortTest {

    @org.junit.jupiter.api.Test
    fun quickSort1() {
        val expected = mutableListOf(-92, -12, 0, 12, 87)
        val test = mutableListOf(0, 12, 87, -12, -92)
        assertEquals(expected, quickSort(test))
    }

    @org.junit.jupiter.api.Test
    fun quickSort2() {
        val expected = mutableListOf(-643, -23, -9, 1, 2, 67838)
        val test = mutableListOf(1, -23, -643, 2, 67838, -9)
        assertEquals(expected, quickSort(test))
    }

    @org.junit.jupiter.api.Test
    fun elements1000000() {
        val testList = mutableListOf<Int>()
        repeat(10000000) {
            testList.add(kotlin.random.Random.nextInt(-100000, 100000))
        }
        assertEquals(testList.sorted(), quickSort(testList))
    }
}