package org.frou.games.quarto.core

import org.frou.games.quarto.core.traits.Trait

data class Piece(val traits: List<Trait>) {

    constructor(vararg manyTraits: Trait) : this(listOf(*manyTraits))

    override fun toString(): String {
        return traits.joinToString(
            separator = " - ",
            prefix = "Piece(",
            postfix = ")",
            transform = { it.toString().subSequence(0, 1) })
    }


}