package me.honkling.skript.parser.patterns.elements

import me.honkling.skript.parser.MatchResult
import me.honkling.skript.parser.patterns.ElementType
import me.honkling.skript.parser.patterns.PatternElement

class OptionalPatternElement(endIndex: Int, val token: SentencePatternElement) : PatternElement(endIndex, ElementType.OPTIONAL) {
    override fun matches(input: String, next: List<PatternElement>): MatchResult {
        val match = token.matches(input, next)
        if (match.matched)
            return match

        val nextMatch = next[0].matches(input, next.subList(1, next.size))

        if (nextMatch.matched)
            return MatchResult(true, "")

        return MatchResult(false, null)
    }

    override fun toString(): String {
        return "[$token]"
    }
}