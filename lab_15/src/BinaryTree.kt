package src

import kotlin.math.pow

class BinaryTree(private var mainList: MutableList<Int?>) {
    constructor(tree: String) : this(mutableListOf<Int?>()) {
        val tempList = mutableListOf<Any>()
        var j = -1
        for (i in tree.indices) {

            if (j > i) {
                continue
            } else if (tree[i] == '(' || tree[i] == ')' || tree[i] == ',') {
                tempList.add(tree[i])
            }
            j = i
            var numberString = ""
            while (tree[j].digitToIntOrNull() != null) {
                numberString += tree[j]
                j++
            }
            if (numberString != "") {
                val resultNumber = numberString.toInt()
                tempList.add(resultNumber)
            }
        }
        var testIndex = 0
        for (i in tempList) {
            when (i) {
                is Int -> {
                    while (testIndex >= mainList.size) {
                        mainList.add(null)
                    }
                    mainList[testIndex] = (i.toInt())
                }

                is Char -> {
                    when (i) {
                        ',' -> {
                            testIndex += 1
                        }

                        '(' -> {
                            testIndex = testIndex * 2 + 1
                        }

                        ')' -> {
                            testIndex = (testIndex - 2) / 2
                        }
                    }
                }
            }
        }
        this.print()
    }

    init {
        if (mainList.size > 1) {
            for (i in ((mainList.size / 2) - 1) downTo 0)
                heapify(i, mainList.size)
        }
        for (count in mainList.indices) {
            mainList[0] =
                mainList[mainList.size - 1 - count].also { mainList[mainList.size - 1 - count] = mainList[0] }
            heapify(0, mainList.size - count - 1)
        }
        println("Куча:")
        this.print()
    }

    private fun print() {
        var count = 1
        for ((index, value) in mainList.withIndex()) {
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
        mainList[largestIndex]?.let { firstIt ->
            if (leftChildIndex < size) {
                mainList[leftChildIndex]?.let { secondIt ->
                    if (secondIt > firstIt) {
                        largestIndex = leftChildIndex
                    }
                }
            }
            if (rightChildIndex < size) {
                mainList[rightChildIndex]?.let { secondIt ->
                    if (secondIt > firstIt) {
                        largestIndex = rightChildIndex
                    }
                }
            }
            if (currentIndex != largestIndex) {
                mainList[currentIndex] =
                    mainList[largestIndex].also { mainList[largestIndex] = mainList[currentIndex] }
//            println(mainArray.joinToString())
                this.heapify(largestIndex, size)
            }
        }

    }


    fun preOrder(index: Int = 0) { // прямой обход
        if (index < mainList.size) {
            println("$index: ${mainList[index]}")
            preOrder(2 * index + 1)
            preOrder(2 * index + 2)
        }
    }

    fun inOrder(index: Int = 0) { // центральный обход
        if (index < mainList.size) {
            inOrder(2 * index + 1)
            println("$index: ${mainList[index]}")
            inOrder(2 * index + 2)
        }
    }

    fun postOrder(index: Int = 0) { // центральный обход
        if (index < mainList.size) {
            postOrder(2 * index + 1)
            postOrder(2 * index + 2)
            println("$index: ${mainList[index]}")
        }
    }

    fun getSortArray(): MutableList<Int?> {
        return mainList
    }
}
