package org.frou.games.quarto.core

import org.frou.games.quarto.core.traits.Color
import org.frou.games.quarto.core.traits.Shape
import org.frou.games.quarto.core.traits.Size
import org.frou.games.quarto.core.traits.Texture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class PieceTest {

    @Test
    fun `Test that one piece equals another with same traits`() {
        assertEquals(Piece(emptySet()), Piece(emptySet()))
        assertEquals(Piece(Size.TINY), Piece(Size.TINY))
        assertEquals(Piece(Size.TINY, Shape.CIRCLE), Piece(Size.TINY, Shape.CIRCLE))
        assertEquals(Piece(Size.TINY, Shape.CIRCLE, Color.WHITE), Piece(Size.TINY, Shape.CIRCLE, Color.WHITE))
        assertEquals(
            Piece(Size.TINY, Shape.CIRCLE, Color.WHITE, Texture.FILLED),
            Piece(Size.TINY, Shape.CIRCLE, Color.WHITE, Texture.FILLED)
        )
    }

    @Test
    fun `Test that one piece is different from another if a single trait differs`() {
        assertNotEquals(Piece(Size.TINY), Piece(Size.BIG))
        assertNotEquals(Piece(Size.TINY, Color.WHITE), Piece(Size.TINY, Color.BLACK))
    }

    @Test
    fun `Test toString works as intended`() {
        assertEquals(
            "Piece(T - W - C - H)",
            Piece(Size.TINY, Color.WHITE, Shape.CIRCLE, Texture.HOLLOW).toString()
        )
    }
}