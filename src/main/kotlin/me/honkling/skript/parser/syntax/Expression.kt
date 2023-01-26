package me.honkling.skript.parser.syntax

import kotlin.reflect.KClass

abstract class Expression<T : Any>(val type: KClass<T>, syntax: String) : SyntaxElement(syntax) {
    abstract fun get(): T
}