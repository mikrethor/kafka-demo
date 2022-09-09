package com.xavierbouclet.kafkademo

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kafka.sender.SenderRecord
import reactor.kafka.sender.SenderResult

@Service
class TopicProducer(
    @Value("\${topic.name.producer}") private val topicName: String,
    val reactiveKafkaProducerTemplate: ReactiveKafkaProducerTemplate<String, String>,
    val reactiveKafkaProducerAvroTemplate: ReactiveKafkaProducerTemplate<String, com.xavierbouclet.kafkademo.avro.Message>
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TopicProducer::class.java)
    }

    fun send(message: String): Mono<SenderResult<Void>> {
        LOGGER.info("Payload sent: {} to {}", message, topicName)
        return reactiveKafkaProducerTemplate.send(topicName, message)
    }

    fun send(message: Message): Mono<SenderResult<Void>> {
        LOGGER.info("Payload sent: {} to {}", message, topicName)
//        val avroMessage: org.springframework.messaging.Message<Message> = MessageBuilder.withPayload(message)
//            .build()
        return Mono.just(message)
            .map {
                com.xavierbouclet.kafkademo.avro.Message.newBuilder().setId(it.id.toString()).setMessage(it.message).build()
            }
            .flatMap {

                reactiveKafkaProducerAvroTemplate.send(topicName, it)
                    .doOnSuccess { LOGGER.info("Payload sent ok") }
                    .doOnError { LOGGER.error("Payload sent error") }
            }

    }
}