package eu.fizzystuff.krog.ui

import com.google.common.base.Joiner
import com.google.common.base.Splitter

class MessageBuffer {
    private var messages: MutableList<String> = mutableListOf()

    fun addMessage(message: String) {
        messages.add(message)
    }

    fun printAndFlush(maxLength: Int): Iterator<String> {
        val joinedMessages = Joiner.on(" ").join(messages)
        messages = mutableListOf()
        return Splitter.fixedLength(maxLength).split(joinedMessages).iterator()
    }

    companion object MessageBuffer {
        val instance = MessageBuffer()
    }
}