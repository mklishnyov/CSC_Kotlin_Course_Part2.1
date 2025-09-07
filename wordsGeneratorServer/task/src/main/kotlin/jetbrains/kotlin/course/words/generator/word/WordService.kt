package jetbrains.kotlin.course.words.generator.word

import jetbrains.kotlin.course.words.generator.util.words
import org.springframework.stereotype.Service

@Service
class WordService {

    companion object {
        val numberOfWords = words.size
        val previousWords: MutableMap<String, MutableList<Word>> = mutableMapOf()
    }

    fun generateNextWord(): Word {
        if (words.isEmpty()) {
            error("No more words!")
        }
        return Word(words.removeFirst())
    }

    fun isValidWord(keyWord: String, newWord: String): Boolean {
        if (newWord.isEmpty()) return false

        val keyCounts = keyWord.groupingBy { it }.eachCount()
        val newCounts = newWord.groupingBy { it }.eachCount()

        return newCounts.all { (ch, count) ->
            count <= keyCounts.getOrDefault(ch, 0)
        }
    }

    fun isNewWord(keyWord: String, newWord: String): Boolean {
        val word = Word(newWord)

        previousWords.putIfAbsent(keyWord, mutableListOf())

        return if (previousWords[keyWord]!!.contains(word)) {
            false
        } else {
            previousWords[keyWord]!!.add(word)
            true
        }
    }
}
