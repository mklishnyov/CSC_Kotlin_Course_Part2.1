package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words

@JvmInline

value class Word(val word: String)

data class Card(val id: Identifier, val words: List<Word>, val identifierFactory: IdentifierFactory = IdentifierFactory(), val cards: List<Card> = generateCards()) {
    companion object {
        private const val WORDS_IN_CARD = 4
        private val cardsAmount = words.size / WORDS_IN_CARD
    }
}