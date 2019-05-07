@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package Console

import java.io.File
import java.lang.IllegalArgumentException

fun main(args: Array<String>) {
    val result = execute(args)
    println(result)
}

fun execute(args: Array<String>): String {
    if (args.size < 3) throw IllegalArgumentException()
    val isV = args.contains("-v")
    val isR = args.contains("-r")
    val isI = args.contains("-i")
    val word = args[args.size - 2]
    val file = args.last()
    return Grep(isV, isI, isR, word, file).search()
}

class Grep(private val isV: Boolean, private val isI: Boolean,
           private val isR: Boolean, private val word: String, inputName: String) {
    private val file = File(inputName).readLines()
    private var rezList = mutableListOf<String>()

    fun search(): String =
        if (isR) regexSearch(word)
        else wordSearch(word)

    private fun wordSearch(word: String): String {
        for (i in 0 until file.size) {
            when {
                isV && isI -> if (word.toLowerCase() !in file[i].toLowerCase()) rezList.add(file[i])
                isI && !isV -> if (word.toLowerCase() in file[i].toLowerCase()) rezList.add(file[i])
                isV && !isI -> if (word !in file[i]) rezList.add(file[i])
                word in file[i] -> rezList.add(file[i])
            }
        }
        return rezList.joinToString(separator = "\n")
    }

    private fun regexSearch(r: String): String {
        for (i in 0 until file.size) {
            when {
                isV && isI -> if (!(file[i].toLowerCase().contains(Regex(r.toLowerCase())))) rezList.add(file[i])
                isI && !isV -> if (file[i].toLowerCase().contains(Regex(r.toLowerCase()))) rezList.add(file[i])
                isV && !isI -> if (!file[i].contains(Regex(r))) rezList.add(file[i])
                (file[i].contains(Regex("""[$r]"""))) -> rezList.add(file[i])
            }
        }
        return rezList.joinToString(separator = "\n")
    }
}


/*  val text = File(inputName).readLines()
    val rezFile = File(outputName)
    val rezLinesList = mutableListOf<String>()
    val listOfDiscNum = params.split(" ")

if (listOfDiscNum.isEmpty())
        throw Exception("Введите условия еще раз")
    for (i in 0 until text.size) {

        if (listOfDiscNum.size == 3 && "word" in listOfDiscNum) {
            if (descriptionStr.toLowerCase() !in text[i].toLowerCase())
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 3 && "-r" in listOfDiscNum) {
            val lowerDescr = descriptionStr.toLowerCase()
            if (!text[i].toLowerCase().contains(Regex(lowerDescr)))
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 2 && "word" in listOfDiscNum && "-v" in listOfDiscNum) {
            if (descriptionStr !in text[i])
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 2 && "-r" in listOfDiscNum && "-v" in listOfDiscNum) {
            if (!text[i].contains(Regex(descriptionStr)))
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 2 && "word" in listOfDiscNum && "-i" in listOfDiscNum) {
            if (descriptionStr.toLowerCase() in text[i].toLowerCase())
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 2 && "-r" in listOfDiscNum && "-i" in listOfDiscNum) {
            descriptionStr.toLowerCase()
            if (text[i].toLowerCase().contains(Regex(descriptionStr)))
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 1 && "word" in listOfDiscNum) {
            if (descriptionStr in text[i])
                rezLinesList.add(text[i])

        }
        if (listOfDiscNum.size == 1 && "-r" in listOfDiscNum) {
            if (text[i].contains(Regex(descriptionStr)))
                rezLinesList.add(text[i])
        }
    }

    rezFile.writeText(rezLinesList.joinToString(separator = "\n"))*/