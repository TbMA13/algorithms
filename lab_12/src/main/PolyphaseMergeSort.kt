package src.main

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption


class PolyphaseMergeSort(private var currentFile: File) {
    private var fileFlag = false
    private var numberIndex = 0
    private var partCount = 0
    private val s = 2500
    private var mainSize = 0
    private var part = mutableListOf<Int>()

    init {
        createTempFiles()
        sort()
        writeOutFile()
    }

    // Создание временных файлов
    private fun createTempFiles() {
        for (i in 0..3) {
            File("data/temp/$i").createNewFile()
        }
    }

    // Сортировка
    private fun sort() {
        fileToSortPart()
        var tempFlag = true // true -> 0.txt; false -> 2.txt
        var tempSize = s / 2
        while (tempSize < mainSize) {
            inFilesSort(tempFlag, tempSize)
            tempSize *= 2
            tempFlag = !tempFlag
        }
        fileFlag = tempFlag
    }

    // Вывод итоговой сортировки
    private fun writeOutFile() {
        val reader = BufferedReader(FileReader("data/temp/${if (fileFlag) "0" else "2"}"))
        var result = ""
        reader.readLines().forEach { result += it + "\n"}
        Files.write(Paths.get("data/out.txt"), result.toByteArray())
        for (i in 0..3) {
            File("data/temp/$i").delete()
        }
    }

    // Чтение файла, сортировка каждой части отдельно и запись каждой части в свой временный файл
    private fun fileToSortPart() {
        try {
            BufferedReader(FileReader(currentFile)).use { br ->
                var line: String?
                while (br.readLine().also { line = it } != null) {
                    //  TODO: добавить корутину?
                    if (numberIndex % s == 0 && numberIndex != 0) {
                        part = quickSort(part)
                        mainSize += part.size
                        partWrite(partCount++ % 2)
                    }
                    line?.let {
                        part.add(it.toInt())
                        numberIndex++
                    }
                }
                part = quickSort(part)
                mainSize += part.size
                partWrite(partCount++ % 2)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun inFilesSort(firstFiles: Boolean, currentSize: Int) {
        val fileName = if (firstFiles) 0 else 2
        val readFirstPath = "data/temp/$fileName"
        val readSecondPath = "data/temp/${fileName + 1}"
        val writeFirstPath = "data/temp/${(fileName + 2) % 4}"
        val writeSecondPath = "data/temp/${(fileName + 2) % 4 + 1}"

        val firstReader = BufferedReader(FileReader(readFirstPath))
        val secondReader = BufferedReader(FileReader(readSecondPath))

        val firstPath = Paths.get(writeFirstPath)
        val secondPath = Paths.get(writeSecondPath)
        var writerFlag = true

        var result = ""

        var i = 0
        var j = 0
        var firstValue: String? = firstReader.readLine()
        var secondValue: String? = secondReader.readLine()

        while (firstValue != null || secondValue != null) {

            // выполняется, когда в обоих файлах количество собранных чисел достигают индекса currentSize
            if (j == i && i == currentSize) {
                if (writerFlag) {
                    Files.write(firstPath, result.toByteArray(), StandardOpenOption.APPEND)
                } else {
                    Files.write(secondPath, result.toByteArray(), StandardOpenOption.APPEND)
                }
                writerFlag = !writerFlag
                result = ""
                i = 0
                j = 0
            }

            if (firstValue != null && secondValue != null) { // выполняется, если оба файла еще не закончены
                if (i < currentSize && j < currentSize) {
                    if (firstValue.toInt() < secondValue.toInt()) {
                        result += "$firstValue\n"
                        firstValue = firstReader.readLine()
                        i++
                    } else {
                        result += "$secondValue\n"
                        secondValue = secondReader.readLine()
                        j++
                    }
                } else if (i < currentSize) {
                    result += "$firstValue\n"
                    firstValue = firstReader.readLine()
                    i++
                } else { // j < currentSize
                    result += "$secondValue\n"
                    secondValue = secondReader.readLine()
                    j++
                }
            } else if (firstValue != null) { // когда закончится второй файл
                result += "$firstValue\n"
                firstValue = firstReader.readLine()
                i++
            } else { // когда закончится первый файл
                result += "$secondValue\n"
                secondValue = secondReader.readLine()
                j++
            }
        }
        if (writerFlag) {
            Files.write(firstPath, result.toByteArray(), StandardOpenOption.APPEND)
        } else {
            Files.write(secondPath, result.toByteArray(), StandardOpenOption.APPEND)
        }
        File(readFirstPath).writeText("")
        File(readSecondPath).writeText("")
        firstReader.close()
        secondReader.close()
    }

    // Запись частей во временные файлы
    private fun partWrite(fileName: Int) {
        var result = ""
        part.forEach { result += it.toString() + "\n" }
        part.clear()
        val tempPath = Paths.get("data/temp/$fileName")
        Files.write(tempPath, result.toByteArray(), StandardOpenOption.APPEND)
    }
}