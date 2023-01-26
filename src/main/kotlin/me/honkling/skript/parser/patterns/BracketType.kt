package me.honkling.skript.parser.patterns

enum class BracketType(val opening: Char, val closing: Char) {
    PARENTHESES('(', ')'),
    BRACKET('[', ']'),
    BRACE('{', '}'),
    ANGLE('<', '>')
}

fun Char.isAnyBracket(opening: Boolean): Boolean {
    BracketType.values().forEach { type ->
        if (opening && type.opening == this)
            return@isAnyBracket true
        else if (!opening && type.closing == this)
            return@isAnyBracket true
    }

    return false
}

fun Char.getBracketType(): BracketType {
    BracketType.values().forEach { bracket ->
        if (bracket.opening == this || bracket.closing == this)
            return@getBracketType bracket
    }

    throw IllegalStateException("Tried to find bracket type of non-bracket character.")
}