package me.honkling.skript.parser

import me.honkling.skript.parser.syntax.Effect
import me.honkling.skript.parser.syntax.Event
import me.honkling.skript.parser.syntax.Expression
import me.honkling.skript.parser.syntax.SyntaxElement

object SkriptParser {
    val events = mutableListOf<Event>()
    val effects = mutableListOf<Effect>()
    val expressions = mutableListOf<Expression<*>>()
    val expressionParser = ExpressionParser(this)

    fun registerSyntax(element: SyntaxElement) {
        if (element is Event)
            events.add(element)

        if (element is Effect)
            effects.add(element)
    }
}