package br.com.dev.springredisdemo.controller

import br.com.dev.springredisdemo.dto.MessageDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("messages")
class MessageController {

    @Autowired
    lateinit var template: RedisTemplate<String, Any>

    @PostMapping
    fun send(@RequestBody messageDTO: MessageDTO) {
        println(template.connectionFactory?.connection?.clientName)
        println(template.clientList)

        template.convertAndSend("chat", messageDTO);
    }
}