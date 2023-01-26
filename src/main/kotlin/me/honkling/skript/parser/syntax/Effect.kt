package me.honkling.skript.parser.syntax

abstract class Effect(syntax: String) : SyntaxElement(syntax) {
    abstract fun execute(event: Event)
}
