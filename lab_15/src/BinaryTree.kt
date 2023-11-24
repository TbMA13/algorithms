package src

import kotlin.math.pow

class BinaryTree(private val mainArray: Array<Int>) {
    init {
        if (mainArray.size > 1) {
            for (i in ((mainArray.size / 2) - 1) downTo 0)
                heapify(i, mainArray.size)
        }
        for (count in mainArray.indices) {
            mainArray[0] =
                mainArray[mainArray.size - 1 - count].also { mainArray[mainArray.size - 1 - count] = mainArray[0] }
            heapify(0, mainArray.size - count - 1)
        }
        println("Куча:")
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
//            println(mainArray.joinToString())
            this.heapify(largestIndex, size)
        }

    }

    fun preOrder(index: Int = 0) { // прямой обход
        if (index < mainArray.size) {
            println("$index: ${mainArray[index]}")
            preOrder(2 * index + 1)
            preOrder(2 * index + 2)
        }
    }

    fun inOrder(index: Int = 0) { // центральный обход
        if (index < mainArray.size) {
            inOrder(2 * index + 1)
            println("$index: ${mainArray[index]}")
            inOrder(2 * index + 2)
        }
    }

    fun postOrder(index: Int = 0) { // центральный обход
        if (index < mainArray.size) {
            postOrder(2 * index + 1)
            postOrder(2 * index + 2)
            println("$index: ${mainArray[index]}")
        }
    }

    fun getSortArray(): Array<Int> {
        return mainArray
    }
}