package jetbrains.kotlin.course.card.trainer.stat

import jetbrains.kotlin.course.card.trainer.card.Back
import org.springframework.stereotype.Service

@Service
class StatService {

    companion object {
        private val history: MutableList<Stat> = mutableListOf<Stat>()
    }

    fun getHistory(): List<Stat> = history.reversed()

    fun save(knownBacks: List<String>, unknownBacks: List<String>): Unit {
        val known = knownBacks.map {Back(it)}
        val unknown = unknownBacks.map {Back(it)}
        history.add(Stat(known, unknown))
    }
}
