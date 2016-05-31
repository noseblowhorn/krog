package eu.fizzystuff.krog.main

import com.google.inject.AbstractModule
import com.googlecode.lanterna.screen.AbstractScreen
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import eu.fizzystuff.krog.scenes.visibility.RaycastingVisibilityStrategy
import eu.fizzystuff.krog.scenes.visibility.VisibilityStrategy
import eu.fizzystuff.krog.ui.MessageBuffer
import org.apache.commons.math3.random.MersenneTwister
import org.apache.commons.math3.random.RandomGenerator

class KrogModule : AbstractModule() {

    override fun configure() {
        bind(RandomGenerator::class.java).to(MersenneTwister::class.java)
        bind(VisibilityStrategy::class.java).to(RaycastingVisibilityStrategy::class.java)
//        bind(MessageBuffer.javaClass).to(MessageBuffer.javaClass).asEagerSingleton()

        val terminal = DefaultTerminalFactory().createTerminal();
        val screen = TerminalScreen(terminal)
        screen.startScreen()

        bind(AbstractScreen::class.java).toInstance(screen)
        bind(Terminal::class.java).toInstance(terminal)
    }
}