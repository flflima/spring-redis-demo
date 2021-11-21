package br.com.dev.springredisdemo.dto

import com.fasterxml.jackson.databind.ObjectMapper
import java.time.LocalDateTime

data class MessageDTO(val text: String, val timestamp: LocalDateTime? = LocalDateTime.now())

fun MessageDTO.toStringJson(objectMapper: ObjectMapper): String = objectMapper.writeValueAsString(this)

fun String.toMessageDTO(objectMapper: ObjectMapper): MessageDTO = objectMapper.readValue(this, MessageDTO::class.java)