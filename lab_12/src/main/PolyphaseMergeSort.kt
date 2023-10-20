package src.src.main


class PolyphaseMergeSort(private var noSortList: MutableList<Int>) {
    private var arrayParts = mutableListOf<List<Int>>()
    private var sortFlag = false
    private fun arrayToParts() {
        arrayParts.clear()
        arrayParts = noSortList.chunked(2500).toMutableList()
    }

    init {
        arrayToParts()
    }

    override fun toString(): String {
        return noSortList.toString()
    }

    fun sorted(): MutableList<Int> {
        return if (sortFlag) {
            noSortList
        } else {
            sort()
            noSortList
        }
    }

    fun sort() {
        for (subArrayIndex in arrayParts.indices) {
            arrayParts[subArrayIndex] = quickSort(arrayParts[subArrayIndex].toMutableList())
        }
        val tempBigList = mutableListOf<Int>()
        for (subArrayIndex in arrayParts.indices) {
            tempBigList += arrayParts[subArrayIndex]
        }
        noSortList = mergeSort(tempBigList)
        sortFlag = true
    }
}