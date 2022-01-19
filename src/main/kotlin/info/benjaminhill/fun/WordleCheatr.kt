package info.benjaminhill.`fun`

import java.io.File
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
 fun main() {
    val possibleAnswers = File("possible.txt").readLines().toSet().also {
        println("Total 5 letter possibles: ${it.size}")
    }
    val allowedGuesses = File("guesses.txt").readLines().toSet().also {
        println("Total 5 letter guesses: ${it.size}")
    }

    // Best possible first matches
    val scores = allowedGuesses.associateWith { firstGuess ->
        possibleAnswers.map { secretWord ->
            Game.guessToReply(firstGuess, secretWord).sumOf { it.score() }.toDouble()
        }.average()
    }

    scores.entries.sortedByDescending { it.value }.take(10).forEach { (word, score)->
        println("$word: $score")
    }

    // But what we rally want is to reduce the space to maximize the days where you get it in 3 tries...

}

