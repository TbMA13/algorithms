import src.BinaryTree

fun main() {
//    val testObj = BinaryTree(mutableListOf(32, 423, 0, 3, 24, 54, 23, 456))
//    println(testObj.getSortArray().joinToString())
//    testObj.preOrder()
//    println()
//    testObj.inOrder()
//    println()
//    testObj.postOrder()

    println("\n---------------------------\n")

    val secondTestObj = BinaryTree("8 (3 (1, 6 (4,7)), 10 (, 14(13,)))")
    secondTestObj.preOrder()
    println()
    secondTestObj.inOrder()
    println()
    secondTestObj.postOrder()
}