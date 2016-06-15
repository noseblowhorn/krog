package eu.fizzystuff.krog.ui

import com.google.common.base.Joiner
import org.junit.Assert
import org.junit.Test

class MessageBufferTests {

    @Test
    fun testPollWhenEmpty() {
        val messageBuffer = MessageBuffer()

        Assert.assertEquals("", messageBuffer.poll())
    }

    @Test
    fun testSizeWhenEmpty() {
        val messageBuffer = MessageBuffer()

        Assert.assertEquals(0, messageBuffer.size())
    }

    @Test
    fun testPollWhenSinglePage() {
        val messageBuffer = MessageBuffer()
        messageBuffer.addMessage("chuje")
        messageBuffer.addMessage("muje")

        Assert.assertEquals("chuje muje", messageBuffer.poll())
    }

    @Test
    fun testPollWhenMultiplePages() {
        val messageBuffer = MessageBuffer()
        messageBuffer.addMessage(Joiner.on("").join((1..50).map { "a" }))
        messageBuffer.addMessage(Joiner.on("").join((1..50).map { "b" }))

        Assert.assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa bbbbbbbbbbbbbbbbbbbbbbbbbbbbb", messageBuffer.poll())
        Assert.assertEquals("bbbbbbbbbbbbbbbbbbbbb", messageBuffer.poll())
    }

    @Test
    fun testSize() {
        val messageBuffer = MessageBuffer()
        messageBuffer.addMessage("chuj")
        messageBuffer.addMessage("dupa")
        messageBuffer.addMessage("cipa")

        Assert.assertEquals(14, messageBuffer.size())
    }
}
