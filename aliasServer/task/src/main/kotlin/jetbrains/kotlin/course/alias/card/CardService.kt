package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service

@Service
class CardService {
    private fun generateCards(): List<Card> {
        return words.shuffled().chunked(Card.getWords())
    }
    
    private fun List<String>.toWords(): List<Word> = this.map {Word(it)}

    fun getCardByIndex(index: Int): Card = TODO("Not implemented yet")
}
