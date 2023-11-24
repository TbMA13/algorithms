import src.BinaryTree

fun main() {
    val testObj = BinaryTree(arrayOf(32, 423, 0, -33, 24, 54, 23, 456))
    println(testObj.getSortArray().joinToString())
    testObj.preOrder()
}