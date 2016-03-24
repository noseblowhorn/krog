package eu.fizzystuff.krog.scenes.mainscreen

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import eu.fizzystuff.krog.scenes.LogicNode

class ExitGame : LogicNode {
    override fun process(keyStroke: KeyStroke) {
        if (keyStroke.keyType == KeyType.Escape) {
            System.exit(0)
        }
    }
}