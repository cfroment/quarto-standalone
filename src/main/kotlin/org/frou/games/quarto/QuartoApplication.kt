package org.frou.games.quarto

import org.frou.games.quarto.core.Game
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuartoApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        Game().play()
    }

}

fun main(args: Array<String>) {
    runApplication<QuartoApplication>(*args)
}
