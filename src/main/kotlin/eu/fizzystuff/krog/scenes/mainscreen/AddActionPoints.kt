package eu.fizzystuff.krog.scenes.mainscreen

import com.google.common.base.Preconditions
import eu.fizzystuff.krog.scenes.LogicNode
import eu.fizzystuff.krog.world.WorldState

class AddActionPoints : LogicNode {
    override fun process() {
        WorldState.instance.currentDungeonLevel.actors.map { x ->

            // a speed of 0 is allowed e.g. for the purpose of a paralysis effect
            // be aware that if the player's speed is set to 0 without a timed effect to end it
            // the game will effectively freeze

            Preconditions.checkState(x.speed >= 0);
            x.actionPoints += x.speed
        }
    }
}