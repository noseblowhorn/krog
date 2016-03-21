package eu.fizzystuff.krog.world

import com.googlecode.lanterna.SGR
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor

object PlayerCharacter {
    public val printableEntity: WorldPrintableEntity

    var x: Int
    var y: Int

    init {
        x = 0
        y = 0

        printableEntity = WorldPrintableEntity(TextCharacter('@',
                TextColor.ANSI.BLUE,
                TextColor.ANSI.BLACK,
                SGR.BOLD))
    }


}