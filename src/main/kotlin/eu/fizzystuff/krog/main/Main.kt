package eu.fizzystuff.krog.main

import com.google.inject.Guice
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import eu.fizzystuff.krog.scenes.MainScreenScene
import eu.fizzystuff.krog.world.PlayerCharacter
import eu.fizzystuff.krog.world.WorldState
import eu.fizzystuff.krog.world.dungeongenerators.EmptyCircularCaveGenerator

fun main(args: Array<String>) {

    val terminal = DefaultTerminalFactory().createTerminal();
    val screen = TerminalScreen(terminal)
    screen.startScreen()

    val injector = Guice.createInjector(KrogModule());

    val generator = injector.getInstance(EmptyCircularCaveGenerator::class.java)

    WorldState.currentDungeonLevel = generator.generate(terminal.terminalSize.columns, terminal.terminalSize.rows)
    PlayerCharacter.instance.x = terminal.terminalSize.columns / 2
    PlayerCharacter.instance.y = terminal.terminalSize.rows / 2

    val scene = MainScreenScene()

    while(true) {
        scene.draw(screen)
        scene.acceptInput(terminal.readInput())
    }
}