package org.frou.games.quarto.core.rule

class QuartoException(quartoError: QuartoError) : Exception(quartoError.getMessage()) {

}

interface QuartoError {
    fun getMessage(): String
    fun raise(): Unit = throw QuartoException(this)

}

enum class QuartoRule : QuartoError {
    POSITION_ALREADY_OCCUPIED {
        override fun getMessage(): String = "Position is already occupied"

    }
}