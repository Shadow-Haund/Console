
package Console

import org.junit.jupiter.api.Assertions.assertEquals // импортировал jupiter
import org.junit.jupiter.api.Test
import java.io.File

private fun assertFileContent(name: String, expectedContent: String) {
    val file = File(name)
    val content = file.readLines().joinToString("\n")
    assertEquals(expectedContent, content)
}

class HelloTest {
    @Test
    fun operatiom() {
        operation("input/text1.txt", "input/text2.txt", "-i -v -r", "[а-я]")
        assertFileContent("input/text2.txt",
                """English words to
some kind of bullshit TO
SDKS LACKS KKK""")
        File("input/text2.txt")
        operation("input/text1.txt", "input/text2.txt", "-i -v word", "Во")
        assertFileContent("input/text2.txt",
                """English words to
some kind of bullshit TO
ФЫВА НЫВА ФИЗОВА
SDKS LACKS KKK""")
        File("input/text2.txt")
        operation("input/text1.txt", "input/text2.txt", "-i -r", "[a-z]")
        assertFileContent("input/text2.txt",
                """English words to
some kind of bullshit TO
SDKS LACKS KKK""")
        File("input/text2.txt")
        operation("input/text1.txt", "input/text2.txt", "-i word", "TO")
        assertFileContent("input/text2.txt",
                """English words to
some kind of bullshit TO""")
        File("input/text2.txt")
        operation("input/text1.txt", "input/text2.txt", "-v -r", "[a-z]")
        assertFileContent("input/text2.txt",
                """Русские слова во
какая то херня Во
ФЫВА НЫВА ФИЗОВА
SDKS LACKS KKK""")
        File("input/text2.txt")
        operation("input/text1.txt", "input/text2.txt", "-v word", "Русские")
        assertFileContent("input/text2.txt",
                """English words to
какая то херня Во
some kind of bullshit TO
ФЫВА НЫВА ФИЗОВА
SDKS LACKS KKK""")
        File("input/text2.txt")
        operation("input/text1.txt", "input/text2.txt", "-r", "[a-z]")
        assertFileContent("input/text2.txt",
                """English words to
some kind of bullshit TO""")
        File("input/text2.txt")
        operation("input/text1.txt", "input/text2.txt", "word", "to")
        assertFileContent("input/text2.txt",
                """English words to""")
        File("input/text2.txt")
    }
}
