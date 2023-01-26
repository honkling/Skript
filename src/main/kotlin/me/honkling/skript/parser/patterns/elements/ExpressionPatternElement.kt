package me.honkling.skript.parser.patterns.elements

import me.honkling.skript.parser.MatchResult
import me.honkling.skript.parser.SkriptParser
import me.honkling.skript.parser.patterns.ElementType
import me.honkling.skript.parser.patterns.PatternElement
import me.honkling.skript.parser.syntax.ClassInfo

class ExpressionPatternElement(endIndex: Int, val classInfo: ClassInfo) : PatternElement(endIndex, ElementType.EXPRESSION) {
    override fun matches(input: String, next: List<PatternElement>): MatchResult {
        try {
            val match = SkriptParser.expressionParser.parseExpression(input, classInfo.clazz)
            return MatchResult(true, )
        }
    }

}