package org.frou.games.quarto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuartoApplication

fun main(args: Array<String>) {
	runApplication<QuartoApplication>(*args)
}
