package br.com.dev.springredisdemo.queue

import br.com.dev.springredisdemo.dto.MessageDTO

class Receiver {
    fun receiveMessage(messageDTO: MessageDTO) {
        println("---------------------------------------")
//        Thread.sleep(5000)
        println("${messageDTO.text} - ${messageDTO.timestamp}")
    }
}
