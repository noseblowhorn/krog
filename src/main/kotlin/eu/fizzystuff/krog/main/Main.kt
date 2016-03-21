package eu.fizzystuff.krog.main

import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import eu.fizzystuff.krog.scenes.MainScreenScene
import eu.fizzystuff.krog.world.DungeonLevel
import eu.fizzystuff.krog.world.PlayerCharacter
import eu.fizzystuff.krog.world.WorldState

fun main(args: Array<String>) {

    val terminal = DefaultTerminalFactory().createTerminal();
    val screen = TerminalScreen(terminal)
    screen.startScreen()

    WorldState.currentDungeonLevel = DungeonLevel(terminal.terminalSize.columns, terminal.terminalSize.rows)
    PlayerCharacter.instance.x = 10
    PlayerCharacter.instance.y = 10

    val scene = MainScreenScene()

    while(true) {
        scene.draw(screen)
        scene.acceptInput(terminal.readInput())
    }
}