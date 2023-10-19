package test
fun quickSort(currentArray: MutableList<Int>): MutableList<Int> {
    if (currentArray.size < 2) {
        return currentArray
    }
    val mainItem = currentArray[0]
    val leftArray = mutableListOf<Int>()
    val rightArray = mutableListOf<Int>()
    for (i in 1..<currentArray.size) {
        if (currentArray[i] < mainItem) {
            leftArray.add(currentArray[i])
        } else {
            rightArray.add(currentArray[i])
        }
    }
    val firstTemp = quickSort(leftArray).toMutableList()
    val secondTemp = quickSort(rightArray).toMutableList()
    var count = 1
    for ((index, value) in firstTemp.withIndex()){
        currentArray[index] = value
        count++
    }
    currentArray[firstTemp.size] = mainItem
    for ((index, value) in secondTemp.withIndex()){
        currentArray[index + count] = value
    }
    return currentArray
}

fun main() {
    val test = mutableListOf(0, 12, 87, -12, -92)
    println(quickSort(test))
}