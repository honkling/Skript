package me.honkling.skript.parser.patterns.elements

import me.honkling.skript.parser.MatchResult
import me.honkling.skript.parser.patterns.ElementType
import me.honkling.skript.parser.patterns.PatternElement

class LiteralPatternElement(endIndex: Int, val token: String) : PatternElement(endIndex, ElementType.LITERAL) {
    override fun matches(input: String, _next: List<PatternElement>): MatchResult {
        return MatchResult(
            input.startsWith(token),
            Regex("^\\S+").find(input)?.value
        )
    }

    override fun toString(): String {
        return token
    }
}