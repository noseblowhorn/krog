package eu.fizzystuff.krog.main

import com.google.inject.Guice
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import eu.fizzystuff.krog.scenes.MainScreenScene
import eu.fizzystuff.krog.scenes.visibility.VisibilityStrategy
import eu.fizzystuff.krog.model.PlayerCharacter
import eu.fizzystuff.krog.model.WorldState
import eu.fizzystuff.krog.model.dungeongenerators.EmptyCircularCaveGenerator
import eu.fizzystuff.krog.model.dungeongenerators.RandomWalkCaveGenerator
import eu.fizzystuff.krog.scenes.SceneTransitionAutomaton

fun main(args: Array<String>) {
    val injector = Guice.createInjector(KrogModule());

    val generator = injector.getInstance(RandomWalkCaveGenerator::class.java)

    WorldState.instance.currentDungeonLevel = generator.generate(80, 22)
    WorldState.instance.currentDungeonLevel.addActor(PlayerCharacter.instance)

    PlayerCharacter.instance.x = WorldState.instance.currentDungeonLevel.transitionPoints.filter { x -> x.targetLevel == null }.first().x
    PlayerCharacter.instance.y = WorldState.instance.currentDungeonLevel.transitionPoints.filter { x -> x.targetLevel == null }.first().y
    PlayerCharacter.instance.speed = 100

    val automaton = SceneTransitionAutomaton(MainScreenScene::class.java, injector)
    automaton.start()
}