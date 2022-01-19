package info.benjaminhill.`fun`

class Game(
    private val allPossibleSecretWords: Set<String>
) {
    private val wordLength: Int
    private var secretWord: String

    init {
        val (min, max) = allPossibleSecretWords.map { it.length }.let { Pair(it.maxOrNull(), it.minOrNull()) }
        require(min == max)
        wordLength = min!!
        secretWord = allPossibleSecretWords.random()
    }

    fun newGame(newSecretWord: String? = null): Game {
        secretWord = newSecretWord ?: allPossibleSecretWords.random()
        return this
    }

    fun guess(guess: String): Reply {
        return guessToReply(guess, secretWord)
    }

    companion object {
        fun guessToReply(guess:String, secretWord:String): Reply {
            return guess.mapIndexed { i, c ->
                Hint(
                    color = when {
                        c == secretWord[i] -> HintType.GREEN
                        secretWord.contains(c)
                                && guess.indexOfFirst { it==c } == i -> HintType.YELLOW
                        else -> HintType.BLACK
                    },
                    location = i,
                    char = c
                )
            }
        }
    }
}