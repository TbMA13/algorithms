package src

import kotlin.math.pow


class minHeap(private val mainArray: Array<Int>) {
    init {
        println("Ненормальная куча:")
        this.print()
        println()
        if (mainArray.size > 1) {
            for (i in ((mainArray.size / 2)) downTo 0)
                heapify(i, mainArray.size)
        }
        for (count in mainArray.indices) {
            mainArray[0] = mainArray[mainArray.size - 1 - count].also { mainArray[mainArray.size - 1 - count] = mainArray[0] }
            heapify(0, mainArray.size - count - 1)
        }
        println("Нормальная куча:")
        this.print()
    }

    private fun print() {
        var count = 1
        for ((index, value) in mainArray.withIndex()) {
            when (index) {
                0 -> {
                    print("$value")
                }

                2.0.pow(count).toInt() - 1 -> {
                    println()
                    print(value)
                    count++
                }

                else -> {
                    print(" $value")
                }
            }
        }
        println()
    }

    private fun heapify(currentIndex: Int, size: Int) {
        val leftChildIndex = 2 * currentIndex + 1
        val rightChildIndex = 2 * currentIndex + 2
        var largestIndex = currentIndex
        if (leftChildIndex < size && mainArray[leftChildIndex] > mainArray[largestIndex]) {
            largestIndex = leftChildIndex
        }
        if (rightChildIndex < size && mainArray[rightChildIndex] > mainArray[largestIndex]) {
            largestIndex = rightChildIndex
        }
        if (currentIndex != largestIndex) {
            mainArray[currentIndex] = mainArray[largestIndex].also { mainArray[largestIndex] = mainArray[currentIndex] }
            this.heapify(largestIndex, size)
        }
    }

    fun getSortArray(): Array<Int> {
        return mainArray
    }
}