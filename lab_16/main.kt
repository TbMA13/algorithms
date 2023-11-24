import src.BinaryTree

fun main() {
    println("8 (3 (1, 6 (4,7)), 10 (, 14(13,)))")
    val secondTestObj = BinaryTree("8 (3 (1, 6 (4,7)), 10 (, 14(13,)))")
    secondTestObj.preOrder()

    val testObj = BinaryTree(mutableListOf(32, 423, 0, -33, 24, 54, 23, 456))
    println("\n" + testObj.getSortArray().joinToString() + "\n")
    testObj.preOrder()
}