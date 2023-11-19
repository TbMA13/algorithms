import src.HashTable
import java.nio.file.Paths

/**
 * Дан текстовый файл с некоторым текстом на русском или английском языках произвольной длины
 * Выбрав некоторую хеш-функцию, создать хеш-таблицу с:
 * с открытой адресацией */

fun main() {
    val hashTable = HashTable(Paths.get("data/text.txt"))

}