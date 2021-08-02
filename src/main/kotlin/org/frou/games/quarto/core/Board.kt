package org.frou.games.quarto.core

import org.frou.games.quarto.core.rule.QuartoError
import org.frou.games.quarto.core.rule.QuartoException
import org.frou.games.quarto.core.rule.QuartoRule
import org.frou.games.quarto.core.traits.Color
import org.frou.games.quarto.core.traits.Shape
import org.frou.games.quarto.core.traits.Size
import org.frou.games.quarto.core.traits.Texture

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
    val placedPieces = mutableMapOf<Pair<Int, Int>, Piece>()

    fun play(piece: Piece, x: Int, y: Int): Boolean {
        val coordinate = Pair(x, y)
        if (placedPieces.containsKey(coordinate)) {
            QuartoRule.POSITION_ALREADY_OCCUPIED.raise()
        }
        return checkWin()
    }

    fun checkWin(): Boolean {
        return false
    }
}