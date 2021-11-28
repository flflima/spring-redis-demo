package br.com.dev.springredisdemo

import br.com.dev.springredisdemo.constants.Constants
import br.com.dev.springredisdemo.queue.Receiver
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter


@SpringBootApplication
class SpringRedisDemoApplication {

    @Bean
    fun container(
        connectionFactory: RedisConnectionFactory,
        listenerAdapter: MessageListenerAdapter
    ): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(connectionFactory)
        container.addMessageListener(listenerAdapter, PatternTopic(Constants.MESSAGE_QUEUE_NAME))
        return container
    }

    @Bean
    fun listenerAdapter(receiver: Receiver) = MessageListenerAdapter(receiver, Constants.LISTENER_METHOD_NAME)

    @Bean
    fun stringRedisTemplate(connectionFactory: RedisConnectionFactory) = StringRedisTemplate(connectionFactory)

    @Bean
    fun objectMapper() = ObjectMapper().registerModule(JavaTimeModule()).registerModule(
        KotlinModule.Builder()
            .withReflectionCacheSize(512)
            .configure(KotlinFeature.NullToEmptyCollection, false)
            .configure(KotlinFeature.NullToEmptyMap, false)
            .configure(KotlinFeature.NullIsSameAsDefault, false)
            .configure(KotlinFeature.StrictNullChecks, false)
            .build()
    )
}

fun main(args: Array<String>) {
    runApplication<SpringRedisDemoApplication>(*args)
}
