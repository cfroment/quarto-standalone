package org.frou.games.quarto.core

import org.frou.games.quarto.core.traits.Color
import org.frou.games.quarto.core.traits.Shape
import org.frou.games.quarto.core.traits.Size
import org.frou.games.quarto.core.traits.Texture

class Game(
    private val board: Board = Board(),
    private val remainingPieces: MutableSet<Piece> = DEFAULT_PIECES.toMutableSet()
) {

    private fun askPiece(): Piece {
        remainingPieces.forEachIndexed { index, Piece ->
            println("$index  $Piece")
        }
        println("Piece number > ")
        val choice = remainingPieces.elementAt(readLine()!!.toInt())
        remainingPieces.remove(choice)
        return choice
    }

    private fun askCoordinates(): Pair<Int, Int> {
        println("Enter x > ")
        val x = readLine()
        println("Enter y > ")
        val y = readLine()
        return Pair(x!!.toInt(), y!!.toInt())
    }

    fun play() {
        var won = false
        while (remainingPieces.isNotEmpty() && !won) {
            val piece = askPiece()
            val coordinates = askCoordinates()
            won = board.play(piece, coordinates.first, coordinates.second)
            board.log()
        }
    }

    companion object {
        private val DEFAULT_PIECES = setOf(
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
            Piece(Size.TINY, Color.BLACK, Shape.SQUARE, Texture.HOLLOW)
        )
    }
}