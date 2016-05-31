package eu.fizzystuff.krog.scenes.io

import com.google.inject.Singleton
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

@Singleton
class ScreenHolder {
    val screen: Screen

    constructor() {
        val terminal = DefaultTerminalFactory().createTerminal();
        screen = TerminalScreen(terminal)
        screen.startScreen()
    }
}