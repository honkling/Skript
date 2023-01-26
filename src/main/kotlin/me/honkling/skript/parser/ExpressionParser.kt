package me.honkling.skript.parser

import me.honkling.skript.parser.syntax.Effect
import me.honkling.skript.parser.syntax.Expression
import kotlin.reflect.KClass

class ExpressionParser(private val parser: SkriptParser) {
    fun parseEffect(input: String): Effect {
        return parser
            .effects
            .find { it.pattern.compiledPattern.matches(input, listOf()).matched }
            ?: throw IllegalArgumentException("Couldn't identify input as effect")
    }

    fun <T : Any> parseExpression(input: String, type: KClass<T>): Expression<T> {
        return parser
            .expressions
            .find { it.pattern.compiledPattern.matches(input, listOf()).matched && it.type == type } as Expression<T>?
            ?: throw IllegalArgumentException("Couldn't identify input as expression")
    }
}