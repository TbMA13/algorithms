package src

import kotlin.math.pow

class BinaryTree(private var mainList: MutableList<Int?>) {
    private val stack = mutableListOf(0)

    constructor(tree: String) : this(mutableListOf<Int?>()) {
//        val tempArray = arrayOf(0)
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
//        val mainList = mutableListOf<Int?>()
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


    fun preOrder() { // прямой обход

        while (stack.isNotEmpty()) {
            val currentIndex = stack.removeLast()
            println(mainList[currentIndex])
            if (currentIndex * 2 + 2 < mainList.size) {
                stack.add(currentIndex * 2 + 2)
            }
            if (currentIndex * 2 + 1 < mainList.size) {
                stack.add(currentIndex * 2 + 1)
            }
        }
    }

    fun getSortArray(): MutableList<Int?> {
        return mainList
    }
}