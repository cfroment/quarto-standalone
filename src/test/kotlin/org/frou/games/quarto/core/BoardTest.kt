package org.frou.games.quarto.core

import org.frou.games.quarto.core.traits.Color
import org.frou.games.quarto.core.traits.Shape
import org.frou.games.quarto.core.traits.Size
import org.frou.games.quarto.core.traits.Texture
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class BoardTest {

    lateinit var board: Board

    @BeforeEach
    fun setUp() {
        this.board = Board()
    }

    @Test
    fun `Test that a similar trait gives a win`() {
        assertTrue(
            board.checkWin(
                listOf(
                    Piece(Size.BIG, Color.WHITE, Shape.SQUARE, Texture.FILLED),
                    Piece(Size.BIG, Color.BLACK, Shape.SQUARE, Texture.FILLED),
                    Piece(Size.BIG, Color.WHITE, Shape.CIRCLE, Texture.FILLED),
                    Piece(Size.BIG, Color.WHITE, Shape.SQUARE, Texture.HOLLOW)
                )
            )
        )
    }

    @Test
    fun `Test that no similar trait does not give a win`() {
        assertFalse(
            board.checkWin(
                listOf(
                    Piece(Size.BIG, Color.WHITE, Shape.SQUARE, Texture.FILLED),
                    Piece(Size.BIG, Color.BLACK, Shape.SQUARE, Texture.FILLED),
                    Piece(Size.BIG, Color.WHITE, Shape.CIRCLE, Texture.FILLED),
                    Piece(Size.TINY, Color.WHITE, Shape.SQUARE, Texture.HOLLOW)
                )
            )
        )
    }

    @Test
    fun `Test that less than MAX pieces does not give a win`() {
        assertFalse(
            board.checkWin(
                listOf(
                    Piece(Size.BIG, Color.WHITE, Shape.SQUARE, Texture.FILLED),
                    Piece(Size.BIG, Color.BLACK, Shape.SQUARE, Texture.FILLED),
                    Piece(Size.BIG, Color.WHITE, Shape.CIRCLE, Texture.HOLLOW)
                )
            )
        )
    }

    @Test
    fun `Test play four similar pieces in a row gives the win`() {
        val piece1 = Piece(Size.BIG, Color.WHITE, Shape.SQUARE, Texture.FILLED)
        val piece2 = Piece(Size.BIG, Color.BLACK, Shape.SQUARE, Texture.FILLED)
        val piece3 = Piece(Size.BIG, Color.WHITE, Shape.CIRCLE, Texture.FILLED)
        val piece4 = Piece(Size.BIG, Color.BLACK, Shape.CIRCLE, Texture.FILLED)
        assertFalse(board.play(piece1, 0, 0))
        assertFalse(board.play(piece2, 0, 1))
        assertFalse(board.play(piece3, 0, 2))
        assertTrue(board.play(piece4, 0, 3))
    }
}