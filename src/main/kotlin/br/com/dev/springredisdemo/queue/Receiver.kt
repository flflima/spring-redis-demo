package br.com.dev.springredisdemo.queue

import br.com.dev.springredisdemo.dto.MessageDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Receiver {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    fun receiveMessage(message: String) {
        Thread.sleep((Math.random() * 12000).toLong())
        val messageDTO = objectMapper.readValue(message, MessageDTO::class.java)
        println("${messageDTO.text} - ${messageDTO.timestamp}")
    }
}
