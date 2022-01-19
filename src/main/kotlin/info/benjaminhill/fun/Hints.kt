package info.benjaminhill.`fun`

enum class HintType {
    YELLOW, GREEN, BLACK;

    override fun toString() =
        when (this) {
            GREEN -> "🟩"
            YELLOW -> "🟨"
            BLACK -> "🟫"
        }
    fun score() = when(this) {
        GREEN -> 2
        YELLOW -> 1
        BLACK -> 0
    }
}

class Hint(
    val color: HintType,
    val location: Int,
    val char: Char,
) {
    override fun toString(): String = "$color $location $char"

    fun score() = color.score()
}

typealias Reply = List<Hint>

fun Reply.score() : Int = this.sumOf { it.score() }