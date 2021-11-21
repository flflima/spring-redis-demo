package br.com.dev.springredisdemo.dto

import java.time.LocalDateTime

data class MessageDTO(val text: String, val timestamp: LocalDateTime? = LocalDateTime.now())
