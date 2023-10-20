package src.src.main

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

fun mergeSort(currentArray: MutableList<Int>): MutableList<Int> {
    if (currentArray.size > 1) {
        val divisionSize = if (currentArray.size % 2 == 0) currentArray.size / 2 else currentArray.size / 2 + 1
        val firstSortArray = mergeSort(currentArray.chunked(divisionSize)[0].toMutableList())
        val secondSortArray = mergeSort(currentArray.chunked(divisionSize)[1].toMutableList())
        var i = 0
        var j = 0
        var count = 0
        while (i < firstSortArray.size || j < secondSortArray.size) {
            if (i < firstSortArray.size && j < secondSortArray.size) {
                if (firstSortArray[i] < secondSortArray[j]) {
                    currentArray[count] = firstSortArray[i]
                    i++
                } else {
                    currentArray[count] = secondSortArray[j]
                    j++
                }
            } else if (i < firstSortArray.size) {
                currentArray[count] = firstSortArray[i]
                i++
            } else {
                currentArray[count] = secondSortArray[j]
                j++
            }
            count++
        }
    }
    return currentArray
}