package org.frou.games.quarto.core

import org.frou.games.quarto.core.rule.QuartoRule
import org.frou.games.quarto.core.traits.*

class Board {

    val height: Int = 4
    val width: Int = 4
    val remainingPieces = mutableSetOf(
        Piece(Size.BIG, Color.WHITE, Shape.CIRCLE, Texture.FILLED),
        Piece(Size.BIG, Color.WHITE, Shape.CIRCLE, Texture.HOLLOW),
        Piece(Size.BIG, Color.WHITE, Shape.SQUARE, Texture.FILLED),
        Piece(Size.BIG, Color.WHITE, Shape.SQUARE, Texture.HOLLOW),
        Piece(Size.BIG, Color.BLACK, Shape.CIRCLE, Texture.FILLED),
        Piece(Size.BIG, Color.BLACK, Shape.CIRCLE, Texture.HOLLOW),
        Piece(Size.BIG, Color.BLACK, Shape.SQUARE, Texture.FILLED),
        Piece(Size.BIG, Color.BLACK, Shape.SQUARE, Texture.HOLLOW),
        Piece(Size.TINY, Color.WHITE, Shape.CIRCLE, Texture.FILLED),
        Piece(Size.TINY, Color.WHITE, Shape.CIRCLE, Texture.HOLLOW),
        Piece(Size.TINY, Color.WHITE, Shape.SQUARE, Texture.FILLED),
        Piece(Size.TINY, Color.WHITE, Shape.SQUARE, Texture.HOLLOW),
        Piece(Size.TINY, Color.BLACK, Shape.CIRCLE, Texture.FILLED),
        Piece(Size.TINY, Color.BLACK, Shape.CIRCLE, Texture.HOLLOW),
        Piece(Size.TINY, Color.BLACK, Shape.SQUARE, Texture.FILLED),
        Piece(Size.TINY, Color.BLACK, Shape.SQUARE, Texture.HOLLOW),
    )

    val xPlacedPieces = mutableMapOf<Int, MutableMap<Int, Piece>>()
    val yPlacedPieces = mutableMapOf<Int, MutableMap<Int, Piece>>()

    fun play(piece: Piece, x: Int, y: Int): Boolean {
        if (xPlacedPieces[x]?.get(y) != null) {
            QuartoRule.POSITION_ALREADY_OCCUPIED.raise()
        }

        val won = sequenceOf(
            checkWin(xPlacedPieces[x]?.values.orEmpty()), // win on row
            checkWin(yPlacedPieces[y]?.values.orEmpty()), // win on column
            checkWin(generateSequence(seed = 0) { it + 1 }  // win on first diagonal
                .takeWhile { it < this.width }
                .map { xPlacedPieces[it]?.get(it) }.toList()),
            checkWin(generateSequence(seed = 0) { it + 1 } // win on second diagonal
                .takeWhile { it < this.width }
                .map { xPlacedPieces[it]?.get(width - it) }.toList())
        ).any()

        if (!won) {
            xPlacedPieces[x]?.put(y, piece)
            yPlacedPieces[y]?.put(x, piece)
        }
        return won
    }

    fun checkWin(pieces: Collection<Piece?>): Boolean {
        val actualPieces = pieces.filterNotNull()
        if (actualPieces.size != this.width) return false  // requires MAX pieces to win

        // intersection of all traits not empty ? --> won
        return actualPieces
            .map { it.traits }
            .fold(emptySet<Trait>()) { s1, s2 -> s1.intersect(s2) }
            .isNotEmpty()
    }
}