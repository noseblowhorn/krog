package eu.fizzystuff.krog.model

import com.googlecode.lanterna.SGR
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor

class Tile(val printableEntity: WorldPrintableEntity,
           val traversable: Boolean,
           val translucent: Boolean) {

    companion object Tiles {
        val floorTile = Tile(WorldPrintableEntity(
                TextCharacter('.',
                        TextColor.ANSI.BLACK,
                        TextColor.ANSI.BLACK,
                        SGR.BOLD)), true, true)
        val wallTile = Tile(WorldPrintableEntity(
                TextCharacter('#',
                        TextColor.ANSI.BLACK,
                        TextColor.ANSI.BLACK,
                        SGR.BOLD)), false, false)
    }

}

