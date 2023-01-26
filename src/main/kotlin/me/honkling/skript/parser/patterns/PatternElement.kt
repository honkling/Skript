package me.honkling.skript.parser.patterns

import me.honkling.skript.parser.MatchResult

abstract class PatternElement(val endIndex: Int, val type: ElementType) {
    abstract fun matches(input: String, next: List<PatternElement>): MatchResult
}

enum class ElementType {
    LITERAL,
    EXPRESSION,
    UNION,
    OPTIONAL,
    REGEX,
    SENTENCE
}