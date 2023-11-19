package src

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class HashTable(filePath: Path) {
    private var textFromFile = ""
    private val array = MutableList<MutableList<Int?>>(120) { mutableListOf(null)}

    init {
        Files.newBufferedReader(filePath, Charsets.UTF_8).lines().forEach { textFromFile += it + "\n" }
//        textFromFile.forEach { println("$it, ${hash(it)}") }
        textIndexing()
        outTable()
//        textFromFile.forEach { println(hash(it)) }
    }

    private fun outTable() {
        File("data/out.txt").createNewFile()
        var result = "Table:\n"
        repeat(81) { result += "-"}
        result += "\n"
        for (i in 0..119) {
            result += "|${i}:"
            repeat(3 - i.toString().length) { result += " "}
            result += array[i][0]?.let { if (textFromFile[it] != '\n') " ${textFromFile[it]} " else " \\n" } ?: "   "
            if (i in listOf(9, 19, 29, 39, 49, 59, 69, 79, 89, 99, 109, 119)) {
                result += "|\n"
            }
        }
        repeat(81) { result += "-"}

        result += "\n\nText:\n$textFromFile"
        File("data/out.txt").writeText(result)
    }

    private fun textIndexing() {
        for (index in textFromFile.indices) {
            var currentHash = hash(textFromFile[index].code)
            while (array[currentHash][0] != null && textFromFile[array[currentHash][0]!!] != textFromFile[index]) {
                currentHash = hash(currentHash)
            }
            if (array[currentHash][0] == null) {
                array[currentHash][0] = index
            } else {
                array[currentHash].add(index)
            }
        }
    }

    private fun hash(code: Int): Int {
        return when (code) {

            in 65..90 -> { // ABCDEFGHIJKLMNOPQRSTUVWXYZ
                code - 65 // 0..25
            }

            in 97..122 -> { // abcdefghijklmnopqrstuvwxyz
                code - 71 // 26..51
            }

            in 1040..1071, in 1072..1103 -> { // АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ ; абвгдеёжзийклмнопрстуфхцчшщъыьэюя
                code - 988 // 52..115
            }

            32 -> { // пробел
                116
            }

            10 -> { // \n
                117
            }

            1105 -> { // ё
                118
            }

            1025 -> { // Ё
                119
            }

//            else ->
//                (9 * (inputCode + 7) * (3 * inputCode + 5 + inputCode) + inputCode + 3) % 120
            else -> {throw Exception("Проблемка...")}
        }
    }
}