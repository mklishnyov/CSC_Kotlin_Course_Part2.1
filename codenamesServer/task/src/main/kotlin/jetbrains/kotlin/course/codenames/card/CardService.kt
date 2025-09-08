package jetbrains.kotlin.course.codenames.card

import jetbrains.kotlin.course.codenames.utils.Utils.TOTAL_NUMBER
import jetbrains.kotlin.course.codenames.utils.words
import org.springframework.stereotype.Service

@Service
class CardService {
    fun generateWordsCards(): List<Card> {
        require(words.size >= TOTAL_NUMBER) { error("") }

        val shuffled = words.shuffled()
        val selected = shuffled.take(TOTAL_NUMBER)

        words = shuffled.drop(TOTAL_NUMBER).toMutableList()

        return selected.map { word ->
            Card(WordCardData(word), CardState.Front)
        }
    }
}
