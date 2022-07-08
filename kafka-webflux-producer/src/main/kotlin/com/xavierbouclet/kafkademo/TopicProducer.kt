package com.xavierbouclet.kafkademo

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class TopicProducer(
    @Value("\${topic.name.producer}") private val topicName: String,
    val kafkaTemplate: KafkaTemplate<String, String>
 //   val kafkaTemplate:ReactiveKafkaProducerTemplate<String,String>
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TopicProducer::class.java)
    }

    fun send( message:String) {
        LOGGER.info("Payload sent: {}", message)
        kafkaTemplate.send(topicName, message)
    }
}