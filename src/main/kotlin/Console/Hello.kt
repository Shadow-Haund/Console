@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package Console

import java.io.File



fun main(args: Array<String>) {

}

fun operation (inputName: String, outputName: String, params: String, discriptionStr: String) {
    println("Выберите способ поиска: ")
    println("1. По слову, введите word ")
    println("2. Через регекс, введите -r")
    println("3. Инвертированное условие, введите -v")
    println("4. Игнорируя размер, введите -i\n")
    val text = File("input/text1.txt").readLines()
    val rezFile = File(outputName)
    val rezLinesList = mutableListOf<String>()
    val listOfDiscNum = params.split(" ")

    if (discriptionStr == null || listOfDiscNum.size == 0)
        throw Exception ("Введите условия еще раз")
    for (i in 0 until text.size) {

        if (listOfDiscNum.size == 3 && "word" in listOfDiscNum){
            if (discriptionStr.toLowerCase() !in text[i].toLowerCase())
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 3 && "-r" in listOfDiscNum){
            discriptionStr.toLowerCase()
            if (!text[i].toLowerCase().contains(Regex("""$discriptionStr""")))
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 2 && "word" in listOfDiscNum && "-v" in listOfDiscNum){
            if (discriptionStr !in text[i])
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 2 && "-r" in listOfDiscNum && "-v" in listOfDiscNum){
            if (!text[i].contains(Regex("""$discriptionStr""")))
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 2 && "word" in listOfDiscNum && "-i" in listOfDiscNum){
            if (discriptionStr.toLowerCase() in text[i].toLowerCase())
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 2 && "-r" in listOfDiscNum && "-i" in listOfDiscNum){
            discriptionStr.toLowerCase()
            if (text[i].toLowerCase().contains(Regex("""$discriptionStr""")))
                rezLinesList.add(text[i])
        }
        if (listOfDiscNum.size == 1 && "word" in listOfDiscNum) {
            if (discriptionStr.toString() in text[i])
                rezLinesList.add(text[i])

        }
        if (listOfDiscNum.size == 1 && "-r" in listOfDiscNum) {
            if (text[i].contains(Regex("""$discriptionStr""")))
                rezLinesList.add(text[i])
        }
    }

    rezFile.writeText(rezLinesList.joinToString(separator = "\n"))
    println(rezLinesList)
}