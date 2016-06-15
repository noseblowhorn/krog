package eu.fizzystuff.krog.scenes.aspects

import com.google.inject.Inject
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.screen.AbstractScreen
import eu.fizzystuff.krog.scenes.aspects.DrawingComponent
import eu.fizzystuff.krog.model.WorldState

class MainMapDrawingComponent public @Inject constructor(val screen: AbstractScreen) : DrawingComponent {
    override fun draw(x: Int, y: Int) {
        val dungeonLevel = WorldState.instance.currentDungeonLevel

        for (i in 0..dungeonLevel.width - 1) {
            for (j in 0..dungeonLevel.height - 1) {
                val actor = dungeonLevel.getActorAt(i, j)
                if (actor != null && dungeonLevel.visible[i][j]) {
                    screen.setCharacter(i + x, j + y, actor.printableEntity.character)
                } else {
                    if (dungeonLevel.seen[i][j]) {
                        screen.setCharacter(i + x, j + y, dungeonLevel.getPrintableEntityAt(i, j).character)
                    } else {
                        screen.setCharacter(i + x, j + y, TextCharacter(' ', TextColor.ANSI.BLACK, TextColor.ANSI.BLACK))
                    }
                }
            }
        }
    }
}