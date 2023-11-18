import src.main.PolyphaseMergeSort
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

const val FILE_PATH = "data/main.txt"
fun randomWriteInFile(count: Int, min: Int, max: Int) {
    val file = File(FILE_PATH)
    file.createNewFile()
    val path = Paths.get(FILE_PATH)
    var writeString = ""
    repeat(count) { writeString += kotlin.random.Random.nextInt(min, max).toString() + "\n" }
    Files.newBufferedWriter(path, Charsets.UTF_8).use { it.write(writeString) }
}

fun main() {
    randomWriteInFile(10000, -100000, 100000)
    val file = File(FILE_PATH)
    val testSort = PolyphaseMergeSort(file)
}