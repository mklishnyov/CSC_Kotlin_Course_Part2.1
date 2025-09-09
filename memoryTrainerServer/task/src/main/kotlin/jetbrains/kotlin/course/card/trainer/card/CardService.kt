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

        fun generateNewCardsSequence()
    }



    fun getNextCard(): Card = TODO("Not implemented yet")

    fun startNewGame(): Card = TODO("Not implemented yet")
}
