package org.frou.games.quarto.core

import org.frou.games.quarto.core.rule.QuartoRule
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Board(private val size: Int = DEFAULT_SIZE) {

    private val xPlacedPieces = mutableMapOf<Int, MutableMap<Int, Piece>>()
    private val yPlacedPieces = mutableMapOf<Int, MutableMap<Int, Piece>>()


    fun play(piece: Piece, x: Int, y: Int): Boolean {
        if (xPlacedPieces[x]?.get(y) != null) {
            QuartoRule.POSITION_ALREADY_OCCUPIED.raise()
        }

        LOGGER.info("Trying to place $piece at [$x, $y]")

        xPlacedPieces.getOrPut(x) { mutableMapOf() }[y] = piece
        yPlacedPieces.getOrPut(y) { mutableMapOf() }[x] = piece

        LOGGER.info("x-board: \n$xPlacedPieces")
        LOGGER.info("y-board: \n$yPlacedPieces")

        val won = sequenceOf(
            checkWin(xPlacedPieces[x]?.values.orEmpty()), // win on row
            checkWin(yPlacedPieces[y]?.values.orEmpty()), // win on column
            checkWin(generateSequence(seed = 0) { it + 1 }  // win on first diagonal
                .takeWhile { it < this.size }
                .map { xPlacedPieces[it]?.get(it) }.toList()),
            checkWin(generateSequence(seed = 0) { it + 1 } // win on second diagonal
                .takeWhile { it < this.size }
                .map { xPlacedPieces[it]?.get(size - it) }.toList())
        ).any { it }

        LOGGER.info("Game won: $won")
        return won
    }

    fun log() {
        for (x in 0..3) {
            for (y in 0..3) {
                print("|${xPlacedPieces[x]?.get(y)?.toShortString() ?: "    "}")
            }
            print("|\n")
        }
    }

    fun checkWin(pieces: Collection<Piece?>): Boolean {
        val actualPieces = pieces.filterNotNull()
        if (actualPieces.size != this.size) return false  // requires MAX pieces to win

        // intersection of all traits not empty ? --> won
        return actualPieces
            .map { it.traits }
            .fold(actualPieces.first().traits) { traits1, traits2 -> traits1.intersect(traits2) }
            .isNotEmpty()
    }

    companion object {
        const val DEFAULT_SIZE = 4
        private val LOGGER: Logger = LoggerFactory.getLogger(Board::class.java)
    }
}