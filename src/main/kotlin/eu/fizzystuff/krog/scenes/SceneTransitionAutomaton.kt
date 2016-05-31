package eu.fizzystuff.krog.scenes

import com.google.inject.Guice
import com.google.inject.Injector

class SceneTransitionAutomaton(initialScene: Class<out Scene>, val injector: Injector) {

    var scene: Scene = injector.getInstance(initialScene)

    fun start() {
        scene.init()
        do {
            val transition = scene.run()
            scene.destroy()
            if (transition != null) {
                scene = injector.getInstance(transition.targetScene)
                scene.init()
            }
        } while (transition != null)
    }

}