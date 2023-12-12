import src.BinaryTree

fun main() {
    val secondTestObj = BinaryTree("8 (3 (1, 6 (4,7)), 10 (, 14(13, 15)))")
    println()

    println(secondTestObj.find(10))
    println(secondTestObj.find(-12))
    secondTestObj.add(999)
    println(secondTestObj.find(999))
    secondTestObj.add(0)
    println(secondTestObj.find(0))
    println()

    while (secondTestObj.size > 0) {
        secondTestObj.remove(0)
        secondTestObj.print()
        println('\n')
    }
}