package me.honkling.skript.parser.patterns

import me.honkling.skript.parser.patterns.elements.*

class Pattern(val pattern: String) {
    lateinit var compiledPattern: SentencePatternElement

    fun compile(): Pattern {
        compiledPattern = parseSentence(0)
        return this
    }

    private fun parseSentence(index: Int): SentencePatternElement {
        val elements = mutableListOf<PatternElement>()
        val restCode = pattern.substring(index)
        var charIndex = 0

        while (charIndex < restCode.length && !restCode[charIndex].isAnyBracket(false) && restCode[charIndex] != '|') {
            val char = restCode[charIndex]

            if (char.isWhitespace()) {
                charIndex++
                continue
            }

            elements.add(parseElement(index + charIndex))
            charIndex = elements.last().endIndex - index
        }

        return SentencePatternElement(elements.last().endIndex, elements)
    }

    private fun parseElement(index: Int): PatternElement {
        val restCode = pattern.substring(index)
        val char = restCode[0]

        if (!char.isAnyBracket(true))
            return parseLiteral(index)

        return when (char) {
            BracketType.PARENTHESES.opening -> parseUnion(index)
            BracketType.BRACKET.opening -> parseOptional(index)
            BracketType.ANGLE.opening -> parseRegex(index)
            else -> throw IllegalStateException("Tried to parse invalid element")
        }
    }

    private fun parseRegex(index: Int): RegexPatternElement {
        if (pattern[index] != BracketType.ANGLE.opening)
            throw IllegalStateException("Tried to parse non-regex")

        val next = getNextBracket(BracketType.ANGLE, index)
        val regex = Regex("^${pattern.substring(index + 1, next)}")

        return RegexPatternElement(next + 1, regex)
    }

    private fun parseOptional(index: Int): OptionalPatternElement {
        if (pattern[index] != BracketType.BRACKET.opening)
            throw IllegalStateException("Tried to parse non-optional")

        val sentence = parseSentence(index + 1)

        if (pattern[sentence.endIndex] != ']')
            throw IllegalStateException("Optional didn't end with bracket")

        return OptionalPatternElement(sentence.endIndex + 1, sentence)
    }

    private fun parseUnion(index: Int): UnionPatternElement {
        if (pattern[index] != BracketType.PARENTHESES.opening)
            throw IllegalStateException("Tried to parse non-union")

        val next = getNextBracket(BracketType.PARENTHESES, index)
        val union = pattern.substring(index, next + 1)
        val inputs = mutableListOf<SentencePatternElement>()
        var charIndex = 0

        while (charIndex < union.length) {
            if (union[charIndex] == '(' || union[charIndex] == ')') {
                charIndex++
                continue
            }

            inputs.add(parseSentence(index + charIndex))
            charIndex = inputs.last().endIndex - index

            if (union[charIndex] != ')' && union[charIndex] != '|')
                throw IllegalStateException("Expected pipe or closing parentheses when parsing union")

            charIndex++
        }

        return UnionPatternElement(inputs.last().endIndex + 1, inputs)
    }

    private fun parseLiteral(index: Int): LiteralPatternElement {
        val restCode = pattern.substring(index)
        val match = Regex("^[^()\\[\\]{}<>|\\s]+").find(restCode)!!.value
        return LiteralPatternElement(index + match.length, match)
    }

    private fun getNextBracket(type: BracketType, index: Int): Int {
        val restPattern = pattern.substring(index)

        if (restPattern[0] != type.opening)
            throw IllegalStateException("Expected opening bracket '${type.opening}', found ${restPattern[0]} instead")

        var charIndex = 1

        while (charIndex < restPattern.length) {
            val char = restPattern[charIndex]

            if (char.isAnyBracket(true)) {
                val type = char.getBracketType()
                charIndex = getNextBracket(type, index + charIndex) + 1 - index
                continue
            }

            if (char == type.closing)
                return index + charIndex

            charIndex++
        }

        throw IllegalArgumentException("Couldn't find next bracket")
    }
}