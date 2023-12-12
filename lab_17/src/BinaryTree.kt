package src

import kotlin.math.pow

class BinaryTree(private var mainList: MutableList<Int?>) {
    var size = 0

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
                    size++
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
        println("Куча:")
        this.print()
    }

    fun print() {
        if (mainList.isEmpty()) {
            println("Элементов нет")
            return
        }
        var count = 1
        var lastIndex = mainList.size - 1
        while (mainList[lastIndex] == null) {
            lastIndex--
        }
        if (lastIndex == 0) {
            print(mainList[0])
            return
        }
        var lastLineNumber = 0
        while (lastLineNumber * 2 + 2 <= lastIndex) {
            lastLineNumber++
        }
        val lastLineSymbolsCount = 5 * 2.0.pow(lastLineNumber - 3).toInt()
        for ((index, value) in mainList.withIndex()) {
            when (index) {
                2.0.pow(count).toInt() - 1 -> {
                    var tempString = ""
                    lastLineNumber = 0
                    while (lastLineNumber * 2 + 2 <= index) {
                        lastLineNumber++
                    }
                    repeat(lastLineSymbolsCount / 2 - 5 * 2.0.pow(lastLineNumber - 1).toInt()) { tempString += " " }
                    println()
//                    print(/*tempString + */value)
                    var tempValue = value.toString()
                    when (tempValue.length) {
                        1 -> tempValue = "$tempValue   "
                        2 -> tempValue = "$tempValue  "
                        3 -> tempValue = "$tempValue "
                    }
                    print(tempValue)
                    count++
                }

                0 -> {
//                    var tempString = ""
//                    repeat(lastLineSymbolsCount / 2 + 2) { tempString += " " }
//                    print(/*tempString + */value)
                    var tempValue = value.toString()
                    when (tempValue.length) {
                        1 -> tempValue = "$tempValue   "
                        2 -> tempValue = "$tempValue  "
                        3 -> tempValue = "$tempValue "
                    }
                    print(tempValue)
                }

                else -> {
                    var tempValue = value.toString()
                    when (tempValue.length) {
                        1 -> tempValue = " $tempValue  "
                        2 -> tempValue = " $tempValue "
                        3 -> tempValue = " $tempValue"
                    }
                    print(" $tempValue")
                }
            }
        }
        println()
    }

//    private fun heapify(currentIndex: Int, size: Int) {
//        val leftChildIndex = 2 * currentIndex + 1
//        val rightChildIndex = 2 * currentIndex + 2
//        var largestIndex = currentIndex
//        mainList[largestIndex]?.let { firstIt ->
//            if (leftChildIndex < size) {
//                mainList[leftChildIndex]?.let { secondIt ->
//                    if (secondIt > firstIt) {
//                        largestIndex = leftChildIndex
//                    }
//                }
//            }
//            if (rightChildIndex < size) {
//                mainList[rightChildIndex]?.let { secondIt ->
//                    if (secondIt > firstIt) {
//                        largestIndex = rightChildIndex
//                    }
//                }
//            }
//            if (currentIndex != largestIndex) {
//                mainList[currentIndex] =
//                    mainList[largestIndex].also { mainList[largestIndex] = mainList[currentIndex] }
//                this.heapify(largestIndex, size)
//            }
//        }
//
//    }

    fun find(targetNumber: Int): Int? {
        var flag = false
        var index = 0
        while (!flag) {
            if (index >= mainList.size) {
                index = -1
                break
            }
            if (mainList[index] == null) {
                index = -1
                break
            }
            mainList[index]?.let {
                if (targetNumber < it) {
                    index = index * 2 + 1
                } else if (targetNumber == it) {
                    flag = true
                } else {
                    index = index * 2 + 2
                }
            }
        }
        return if (index != -1) index else null
    }

    fun remove(index: Int) {
        if (index < 0 || index >= mainList.size) {
            throw IndexOutOfBoundsException(index)
        } else if (mainList.size == 1) {
            mainList.removeAt(0)
            size--
            return
        }
        val leftChildIndex: (Int) -> Int = { i: Int -> i * 2 + 1 }
        val rightChildIndex: (Int) -> Int = { i: Int -> i * 2 + 2 }
        val leftBool = leftChildIndex(index) < mainList.size && mainList[leftChildIndex((index))] != null
        val rightBool = rightChildIndex(index) < mainList.size && mainList[rightChildIndex(index)] != null
        if (!leftBool && !rightBool) {
            mainList[index] = null
            size--
        } else if (leftBool && !rightBool) {
            mainList[index] = mainList[leftChildIndex(index)]
            remove(leftChildIndex(index))
        } else if (!leftBool /*&& rightBool*/) {
            mainList[index] = mainList[rightChildIndex(index)]
            remove(rightChildIndex(index))
        } else if (rightChildIndex(leftChildIndex(index)) < mainList.size && mainList[rightChildIndex(index)] != null) {
            var tempIndex = leftChildIndex(index)
            while (rightChildIndex(tempIndex) < mainList.size && mainList[rightChildIndex(tempIndex)] != null) {
                tempIndex = rightChildIndex(tempIndex)
            }
            mainList[index] = mainList[tempIndex].also { remove(tempIndex) }
        } else if (leftChildIndex(rightChildIndex(index)) < mainList.size && mainList[leftChildIndex(
                rightChildIndex(
                    index
                )
            )] != null
        ) {
            var tempIndex = rightChildIndex(index)
            while (leftChildIndex(tempIndex) < mainList.size && mainList[leftChildIndex(tempIndex)] != null) {
                tempIndex = leftChildIndex(tempIndex)
            }
            mainList[index] = mainList[tempIndex].also { remove(tempIndex) }
        } /*else if (!leftBool) {
            mainList[index] = mainList[rightChildIndex(index)]
            remove(rightChildIndex(index))
        } */else {
            mainList[index] = mainList[rightChildIndex(index)].also { remove(rightChildIndex(index)) }
        }
        while (mainList.size != 0 && mainList[mainList.size - 1] == null) {
            mainList.removeAt(mainList.size - 1)
        }
    }

    fun add(element: Int) {
        var index = 0
        while (true) {
            while (index >= mainList.size) {
                mainList.add(null)
            }
            if (mainList[index] == null) {
                mainList[index] = element
                break
            }
            mainList[index]?.let {
                index = if (element < it) {
                    index * 2 + 1
                } else {
                    index * 2 + 2
                }
            }
        }
        size++
    }
}