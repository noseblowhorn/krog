package eu.fizzystuff.krog.scenes.mainscreen

import eu.fizzystuff.krog.scenes.LogicNode
import eu.fizzystuff.krog.model.WorldState

class NpcAI : LogicNode {
    override fun process() {
        val actors = WorldState.instance.currentDungeonLevel.actors

        actors.forEach { x -> x.processAi() }
    }
}