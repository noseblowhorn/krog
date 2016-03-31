package eu.fizzystuff.krog.main

import com.google.inject.Guice
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import eu.fizzystuff.krog.scenes.MainScreenScene
import eu.fizzystuff.krog.scenes.visibility.VisibilityStrategy
import eu.fizzystuff.krog.world.PlayerCharacter
import eu.fizzystuff.krog.world.WorldState
import eu.fizzystuff.krog.world.dungeongenerators.EmptyCircularCaveGenerator
import eu.fizzystuff.krog.world.dungeongenerators.RandomWalkCaveGenerator

fun main(args: Array<String>) {

    val terminal = DefaultTerminalFactory().createTerminal();
    val screen = TerminalScreen(terminal)
    screen.startScreen()

    val injector = Guice.createInjector(KrogModule());

//    val generator = injector.getInstance(EmptyCircularCaveGenerator::class.java)
    val generator = injector.getInstance(RandomWalkCaveGenerator::class.java)

    WorldState.instance.currentDungeonLevel = generator.generate(80, 22)
    WorldState.instance.currentDungeonLevel.addActor(PlayerCharacter.instance)

    PlayerCharacter.instance.x = WorldState.instance.currentDungeonLevel.transitionPoints.filter { x -> x.targetLevel == null }.first().x
    PlayerCharacter.instance.y = WorldState.instance.currentDungeonLevel.transitionPoints.filter { x -> x.targetLevel == null }.first().y
    PlayerCharacter.instance.speed = 100

    val scene = MainScreenScene()

    scene.init()

    while(true) {
        scene.draw(screen)
        scene.tick()
        scene.acceptInput(terminal.readInput())
    }
}