package me.honkling.skript.effects

import me.honkling.skript.parser.syntax.Effect
import me.honkling.skript.parser.syntax.Event

object TestEffect : Effect("ready the (test|experiment) [carefully]") {
    override fun execute(event: Event) {
        println("Testy!")
    }

    override fun init() {
        println("inity.")
    }
}