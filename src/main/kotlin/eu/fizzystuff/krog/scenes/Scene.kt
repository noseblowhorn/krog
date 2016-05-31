package eu.fizzystuff.krog.scenes

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.AbstractScreen

abstract class Scene {
    abstract fun init()
    abstract fun destroy()

    /**
     * @return sceneTransition The next scene to transition to, or null on game exit.
     */
    abstract fun run(): SceneTransition?

}