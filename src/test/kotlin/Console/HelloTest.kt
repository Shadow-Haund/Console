package Console

import org.junit.jupiter.api.Assertions.assertEquals // импортировал jupiter
import org.junit.jupiter.api.Test
import java.io.File

private fun assertFileContent(name: String, expectedContent: String) {
    val file = File(name)
    val content = file.readLines().joinToString("\n")
    assertEquals(expectedContent, content)
}
fun listOfPar(params:String) = params.split(" ")

class HelloTest {
    @Test
    fun operation1() {
        val args = "-i -v -r [а-я] testData/text1.txt".split(" ").toTypedArray()
        val res = execute(args)
        assertEquals("""English words to
some kind of text TO
SDKS LACKS KKK""", res)
    }
    @Test
    fun operation2() {
        val args = "-i -v Во testData/text1.txt".split(" ").toTypedArray()
        val res = execute(args)
        assertEquals("""English words to
some kind of text TO
ФЫВА НЫВА ФИЗОВА
SDKS LACKS KKK""", res)
    }
    @Test
    fun operatiom3() {
        val args = "-i -r [a-z] testData/text1.txt".split(" ").toTypedArray()
        val res = execute(args)
        assertEquals("""English words to
some kind of text TO
SDKS LACKS KKK""", res)
    }
    @Test
    fun operatiom4() {
        val args = "-i word TO testData/text1.txt".split(" ").toTypedArray()
        val res = execute(args)
        assertEquals("""English words to
some kind of text TO""", res)
    }
    @Test
    fun operatiom5() {
        val args = "-v -r [a-z] testData/text1.txt".split(" ").toTypedArray()
        val res = execute(args)
        assertEquals("""Русские слова во
текст разного содержания Во
ФЫВА НЫВА ФИЗОВА
SDKS LACKS KKK""", res)
    }
    @Test
    fun operatiom6() {
        val args = "-v word Русские testData/text1.txt".split(" ").toTypedArray()
        val res = execute(args)
        assertEquals("""English words to
текст разного содержания Во
some kind of text TO
ФЫВА НЫВА ФИЗОВА
SDKS LACKS KKK""", res)
    }
    @Test
    fun operatiom7() {
        val args = "-r [a-z] testData/text1.txt".split(" ").toTypedArray()
        val res = execute(args)
        assertEquals("""English words to
some kind of text TO""", res)
    }
    @Test
    fun operatiom8() {
        val args = "to testData/text1.txt".split(" ").toTypedArray()
        val res = execute(args)
        assertEquals("""English words to""", res)
    }
}
