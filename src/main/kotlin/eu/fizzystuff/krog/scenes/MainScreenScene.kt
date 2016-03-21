package eu.fizzystuff.krog.scenes

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.AbstractScreen
import eu.fizzystuff.krog.scenes.mainscreen.PlayerCharacterMovement
import eu.fizzystuff.krog.scenes.visibility.RaycastingVisibilityStrategy
import eu.fizzystuff.krog.world.PlayerCharacter
import eu.fizzystuff.krog.world.WorldState

class MainScreenScene : Scene {
    override fun draw(screen: AbstractScreen) {
        val dungeonLevel = WorldState.currentDungeonLevel

        for (i in 0..dungeonLevel.width - 1) {
            for (j in 0..dungeonLevel.height - 1) {
                if (i == PlayerCharacter.instance.x && j == PlayerCharacter.instance.y) {
                    screen.setCharacter(i, j, PlayerCharacter.instance.printableEntity.character)
                } else {
                    if (dungeonLevel.seen[i][j]) {
                        screen.setCharacter(i, j, dungeonLevel.getPrintableEntityAt(i, j).character)
                    } else {
                        screen.setCharacter(i, j, TextCharacter(' ', TextColor.ANSI.BLACK, TextColor.ANSI.BLACK))
                    }
                }
            }
        }

        screen.refresh()
    }

    override fun acceptInput(input: KeyStroke) {
        PlayerCharacterMovement().process(input)
        RaycastingVisibilityStrategy().calculateVisibility(WorldState.currentDungeonLevel,
                PlayerCharacter.instance.x, PlayerCharacter.instance.y, 4)
    }

}