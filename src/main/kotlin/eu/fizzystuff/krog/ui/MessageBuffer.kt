package eu.fizzystuff.krog.ui

import com.google.common.base.Joiner
import com.google.common.base.Splitter
import com.google.common.collect.ImmutableList
import java.util.*

class MessageBuffer {
    private var messages: MutableList<String> = mutableListOf()

    fun addMessage(message: String) {
        messages.add(message)
    }

    fun poll(len: Int): String {
        val listBuilder = ImmutableList.builder<String>()

        while (listBuilder.build().fold(0, { totalLength, str -> totalLength + str.length + 1 }) < 80 && messages.size > 0) {
            listBuilder.add(messages.get(0))
            messages.removeAt(0)
        }

        val oversizedOutput = Joiner.on(" ").join(listBuilder.build())
        if (oversizedOutput.length > len) {
            messages.add(0, oversizedOutput.substring(len))
        }

        return oversizedOutput.substring(0, Math.min(oversizedOutput.length, len))
    }

    fun size(): Int {
        return messages.fold(0, { acc, v -> acc + v.length }) +
                (if(messages.size > 0) messages.size - 1 else 0)
    }

    fun clear() {
        messages = mutableListOf()
    }
}