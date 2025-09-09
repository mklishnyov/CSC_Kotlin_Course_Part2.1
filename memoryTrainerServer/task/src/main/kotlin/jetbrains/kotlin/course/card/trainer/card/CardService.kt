package jetbrains.kotlin.course.card.trainer.card

import jetbrains.kotlin.course.card.trainer.util.countries
import org.springframework.stereotype.Service

@Service
class CardService {

    companion object {
        val randomCardGenerator = CardSequenceGenerator {
            val generated = countries.map { (key, value) -> Card(Front(key), Back(value)) }
            generated.shuffled()
        }

        private fun generateNewCardsSequence() = randomCardGenerator.generateCards().toMutableList()

        var cards = generateNewCardsSequence()
    }

    fun getNextCard(): Card {
        require(cards.isNotEmpty()) {error("")}
        return cards.removeFirst()
    }

    fun startNewGame(): Card {
        cards = generateNewCardsSequence()
        return getNextCard()
    }
}
