package me.honkling.skript.parser.syntax

import me.honkling.skript.parser.patterns.Pattern

abstract class SyntaxElement(syntax: String) {
    val pattern = Pattern(syntax).compile()

    abstract fun init()
}