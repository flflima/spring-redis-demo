package br.com.dev.springredisdemo.controller

import br.com.dev.springredisdemo.constants.Constants
import br.com.dev.springredisdemo.dto.MessageDTO
import br.com.dev.springredisdemo.dto.toStringJson
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("messages")
class MessageController {

    @Autowired
    lateinit var template: StringRedisTemplate

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @PostMapping
    fun send(@RequestBody messageDTO: MessageDTO) {
        template.convertAndSend(Constants.MESSAGE_QUEUE_NAME, messageDTO.toStringJson(objectMapper));
    }
}