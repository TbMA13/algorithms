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

fun main() {
    val test = mutableListOf(0, 342, 43, 9, -12, 89)
    println(mergeSort(test))
}