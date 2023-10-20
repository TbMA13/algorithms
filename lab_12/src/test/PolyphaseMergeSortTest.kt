package src.test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import src.src.main.PolyphaseMergeSort

class PolyphaseMergeSortTest {

    @Test
    fun noElements() {
        val testSort = PolyphaseMergeSort(mutableListOf())
        testSort.sort()
        assertEquals(mutableListOf<Int>().toString(), testSort.toString())
    }

    @Test
    fun element() {
        val testList = mutableListOf<Int>()
        testList.add(kotlin.random.Random.nextInt(-10000, 10000))
        val testSort = PolyphaseMergeSort(testList)
        testSort.sort()
        assertEquals(testList.sorted().toString(), testSort.toString())
    }

    @Test
    fun elements10000() {
        val testList = mutableListOf<Int>()
        repeat(10000) {
            testList.add(kotlin.random.Random.nextInt(-1000, 1000))
        }
        val testSort = PolyphaseMergeSort(testList)
        testSort.sort()
        assertEquals(testList.sorted().toString(), testSort.toString())
    }

    @Test
    fun elements100000() {
        val testList = mutableListOf<Int>()
        repeat(100000) {
            testList.add(kotlin.random.Random.nextInt(-10000, 10000))
        }
        val testSort = PolyphaseMergeSort(testList)
        testSort.sort()
        assertEquals(testList.sorted().toString(), testSort.toString())
    }

    @Test
    fun elements10000000() {
        val testList = mutableListOf<Int>()
        repeat(10000000) {
            testList.add(kotlin.random.Random.nextInt(-100000, 100000))
        }
        val testSort = PolyphaseMergeSort(testList)
//        testSort.sort()
        assertEquals(testList.sorted(), testSort.sorted())
    }
}