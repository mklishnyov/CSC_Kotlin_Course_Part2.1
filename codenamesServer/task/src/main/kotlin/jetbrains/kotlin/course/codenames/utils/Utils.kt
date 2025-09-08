package jetbrains.kotlin.course.codenames.utils

import jetbrains.kotlin.course.codenames.keyCard.KeyCardCell
import jetbrains.kotlin.course.codenames.keyCard.KeyCardType

object Utils {
    private const val N = 5
    const val TOTAL_NUMBER = N * N
    const val PINK_CARDS_NUMBER = 8
    const val VIOLET_CARDS_NUMBER = 9
    const val GRAY_CARDS_NUMBER = 7
    const val BLACK_CARDS_NUMBER = 1
    init {
        require(PINK_CARDS_NUMBER
                + VIOLET_CARDS_NUMBER
                + GRAY_CARDS_NUMBER
                + BLACK_CARDS_NUMBER == TOTAL_NUMBER) {"IllegalArgumentException"}
    }
    private val previousAttempts: MutableList<List<KeyCardCell>> = mutableListOf()
    val uniqueKeyCardGenerator= KeyCardGenerator {
        var finalList: List<KeyCardCell>
        do {
            val modifiedList = KeyCardType.entries.map { type ->
                List(type.number) { KeyCardCell(type) }
            }
            finalList = modifiedList.flatten().shuffled()
        } while (finalList in previousAttempts)

        previousAttempts.add(finalList)
        finalList

    }

}

fun interface KeyCardGenerator {
    fun generateData(): List<KeyCardCell>
}