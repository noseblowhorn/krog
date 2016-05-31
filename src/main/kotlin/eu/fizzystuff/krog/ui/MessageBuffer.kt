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

    fun size(): Int {
        return messages.fold(0, { acc, v -> acc + v.length }) +
                (if(messages.size > 0) messages.size - 1 else 0)
    }

    companion object MessageBuffer {
        val instance = MessageBuffer()
    }
}