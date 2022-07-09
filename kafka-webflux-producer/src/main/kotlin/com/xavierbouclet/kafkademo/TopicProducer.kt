package com.xavierbouclet.kafkademo

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kafka.sender.SenderResult

@Service
class TopicProducer(
    @Value("\${topic.name.producer}") private val topicName: String,
    val kafkaTemplate: ReactiveKafkaProducerTemplate<String, String>
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TopicProducer::class.java)
    }

    fun send( message:String): Mono<SenderResult<Void>> {
        LOGGER.info("Payload sent: {} to {}", message, topicName)
        return kafkaTemplate.send(topicName, message)
    }
}