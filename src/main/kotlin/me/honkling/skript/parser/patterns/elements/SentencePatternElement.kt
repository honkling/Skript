package me.honkling.skript.parser.patterns.elements

import me.honkling.skript.parser.MatchResult
import me.honkling.skript.parser.patterns.ElementType
import me.honkling.skript.parser.patterns.PatternElement

class SentencePatternElement(
    endIndex: Int,
    val inputs: List<PatternElement>
) : PatternElement(endIndex, ElementType.SENTENCE) {
    override fun matches(input: String, next: List<PatternElement>): MatchResult {
        var index = 0
        var elemIndex = 0

        while (index < input.length && elemIndex < inputs.size) {
            val rest = input.substring(index)

            val match = inputs[elemIndex].matches(rest, inputs.subList(elemIndex + 1, inputs.size))

            if (!match.matched)
                return MatchResult(false, null)

            elemIndex++
            index += match.match!!.length

            if (elemIndex < inputs.size)
                index += Regex("^\\s+").find(input.substring(index))?.value?.length ?: 0
        }

        return MatchResult(true, input.substring(0, index))
    }

    override fun toString(): String {
        return inputs.joinToString(" ")
    }
}