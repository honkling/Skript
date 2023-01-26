package me.honkling.skript.parser.patterns.elements

import me.honkling.skript.parser.MatchResult
import me.honkling.skript.parser.patterns.ElementType
import me.honkling.skript.parser.patterns.PatternElement

class RegexPatternElement(endIndex: Int, val regex: Regex) : PatternElement(endIndex, ElementType.REGEX) {
    override fun matches(input: String, _next: List<PatternElement>): MatchResult {
        return MatchResult(
            regex.containsMatchIn(input),
            regex.find(input)?.value
        )
    }

    override fun toString(): String {
        return "<$regex>"
    }
}