package info.benjaminhill.`fun`

class Player(allWords: Set<String>) {
    private val hints: MutableSet<Hint> = mutableSetOf()

    // Every word starts out as a potential answer
    private val wordToState: MutableMap<String, Boolean> = allWords.associateWith {
        true
    }.toMutableMap()

    fun newGame(): Player {
        hints.clear()
        val entriesToReset = wordToState.filterValues { !it }.keys.associateWith { true }
        wordToState.putAll(entriesToReset)
        return this
    }

    fun getPotentialsSize() = wordToState.filterValues { it }.size

    fun addHint(hint: Hint): Player {
        hints.add(hint)
        wordToState.filterValues { it }.forEach { (word, _) ->
            wordToState[word] = when (hint.color) {
                HintType.GREEN -> word[hint.location] == hint.char
                HintType.YELLOW -> word[hint.location] != hint.char && word.contains(hint.char)
                HintType.BLACK -> !word.contains(hint.char)
            }
        }
        return this
    }
}