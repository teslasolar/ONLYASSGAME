package io.onlyass.game

data class GameState(
    val currentPipe: Int = 0,
    val currentScenario: Int = 0,
    val score: Long = 1,
    val answered: Int = 0,
    val streak: Int = 0,
    val revealed: Boolean = false,
    val selectedChoice: Int = -1,
    val phase: Phase = Phase.MENU,
) {
    val pipe get() = PIPES[currentPipe]
    val scenario get() = pipe.scenarios[currentScenario]
    val coherent get() = score == 510510L
}

enum class Phase { MENU, PLAY, REVEAL, RESULTS }

fun GameState.choose(idx: Int): GameState {
    val choice = scenario.choices[idx]
    val newScore = if (choice.isBreak) score * pipe.prime else score
    val newStreak = if (choice.isBreak) streak + 1 else 0
    return copy(
        selectedChoice = idx,
        revealed = true,
        phase = Phase.REVEAL,
        score = newScore,
        answered = answered + 1,
        streak = newStreak,
    )
}

fun GameState.next(): GameState {
    val nextScenario = currentScenario + 1
    return if (nextScenario < pipe.scenarios.size) {
        copy(currentScenario = nextScenario, revealed = false,
            selectedChoice = -1, phase = Phase.PLAY)
    } else {
        val nextPipe = currentPipe + 1
        if (nextPipe < PIPES.size) {
            copy(currentPipe = nextPipe, currentScenario = 0,
                revealed = false, selectedChoice = -1, phase = Phase.PLAY)
        } else {
            copy(phase = Phase.RESULTS)
        }
    }
}
