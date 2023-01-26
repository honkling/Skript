package me.honkling.skript

import me.honkling.skript.effects.OtherEffect
import me.honkling.skript.effects.TestEffect
import me.honkling.skript.parser.SkriptParser

fun main() {
    println("Hello world!")

    SkriptParser.registerSyntax(TestEffect)
    SkriptParser.registerSyntax(OtherEffect)

    val effect = SkriptParser.expressionParser.parseEffect("ready the super cool experiment")
    println("Found effect $effect")
}