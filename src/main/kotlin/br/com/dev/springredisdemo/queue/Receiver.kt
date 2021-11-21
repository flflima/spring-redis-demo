package br.com.dev.springredisdemo.queue

import br.com.dev.springredisdemo.constants.Constants
import br.com.dev.springredisdemo.dto.toMessageDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Receiver {
    @Autowired
    lateinit var objectMapper: ObjectMapper

    fun receiveMessage(message: String) {
        Thread.sleep((Math.random() * Constants.LIMIT_SECONDS_IN_MS).toLong())
        val messageDTO = message.toMessageDTO(objectMapper)
        println(messageDTO)
    }
}
