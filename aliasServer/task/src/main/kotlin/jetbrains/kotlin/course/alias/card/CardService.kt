package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service

@Service
class CardService(private val identifierFactory: IdentifierFactory = IdentifierFactory()) {

    private val cards: List<Card> = generateCards()
    companion object {
        private const val WORDS_IN_CARD: Int = 4
        val cardsAmount: Int = words.size / WORDS_IN_CARD
    }
    private fun generateCards(): List<Card> {
        return words.shuffled()
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { chunk ->
                Card(identifierFactory.uniqueIdentifier(), chunk.toWords())
            }
    }

    private fun List<String>.toWords(): List<Word> = this.map {Word(it)}

    fun getCardByIndex(index: Int): Card {
        return cards.getOrNull(index)
            ?: error("You use incorrect index 10 for the list")
    }
}
