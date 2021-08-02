package org.frou.games.quarto.core.traits

interface Trait

enum class Size : Trait {
    BIG,
    TINY
}

enum class Color : Trait {
    BLACK,
    WHITE
}

enum class Shape : Trait {
    SQUARE,
    CIRCLE
}

enum class Texture : Trait {
    FILLED,
    HOLLOW
}