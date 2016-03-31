package eu.fizzystuff.krog.world

import com.googlecode.lanterna.SGR
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor

class PlayerCharacter : Actor {

    constructor() :
    super(WorldPrintableEntity(TextCharacter('@',
            TextColor.ANSI.BLUE,
            TextColor.ANSI.BLACK,
            SGR.BOLD)), 0, 0)

    companion object PlayerCharacterHolder {
        var instance = PlayerCharacter()
    }
}