package br.com.dev.springredisdemo

import br.com.dev.springredisdemo.queue.Receiver
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@SpringBootApplication
class SpringRedisDemoApplication {

    @Bean
    fun container(
        connectionFactory: RedisConnectionFactory?,
        listenerAdapter: MessageListenerAdapter?
    ): RedisMessageListenerContainer? {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(connectionFactory!!)
        container.addMessageListener(listenerAdapter!!, PatternTopic("chat"))
        return container
    }

    @Bean
    fun listenerAdapter(receiver: Receiver): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }

    @Bean
    fun receiver(): Receiver {
        return Receiver()
    }
//
//	@Bean
//	fun template(connectionFactory: RedisConnectionFactory): StringRedisTemplate? {
//		return StringRedisTemplate(connectionFactory)
//	}

    @Bean
    fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, Any>? {
        val om = ObjectMapper()
        om.registerModule(JavaTimeModule())
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        val serializer: Jackson2JsonRedisSerializer<*> = Jackson2JsonRedisSerializer(Any::class.java)
        serializer.setObjectMapper(om)
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(factory)
        template.valueSerializer = serializer
//        template.keySerializer = StringRedisSerializer()
//        template.hashKeySerializer = StringRedisSerializer()
//        template.hashValueSerializer = serializer
//        template.afterPropertiesSet()
        return template
    }

}

fun main(args: Array<String>) {
    runApplication<SpringRedisDemoApplication>(*args)
}
