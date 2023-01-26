package me.honkling.skript.parser.patterns.elements

import me.honkling.skript.parser.MatchResult
import me.honkling.skript.parser.patterns.ElementType
import me.honkling.skript.parser.patterns.PatternElement

class UnionPatternElement(endIndex: Int, val inputs: List<SentencePatternElement>) : PatternElement(endIndex, ElementType.UNION) {
    override fun matches(input: String, next: List<PatternElement>): MatchResult {
        inputs.forEach {
            val match = it.matches(input, next)

            if (match.matched)
                return@matches match
        }

        return MatchResult(false, null)
    }

    override fun toString(): String {
        return "(${inputs.joinToString("|")})"
    }
}