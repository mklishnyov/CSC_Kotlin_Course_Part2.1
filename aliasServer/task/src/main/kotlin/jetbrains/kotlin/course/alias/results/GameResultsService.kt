package jetbrains.kotlin.course.alias.results

import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

typealias GameResult = List<Team>

@Service
class GameResultsService {

    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult): Unit {
        // Проверка №1: результат не должен быть пустым
        if (result.isEmpty()) {
            error("Результат игры не может быть пустым")
        }
        // Проверка №2: все команды из результата должны существовать в TeamService.teamsStorage
        if (!result.all { it.id in TeamService.teamsStorage.keys }) {
            error("Некоторые команды отсутствуют в TeamService")
        }
        // если всё ок, сохраняем
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> = gameHistory.reversed()
}
