package com.xavierbouclet.kafkaconsumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class TopicListener(
    @Value("\${topic.name.consumer}")
    private val topicName: String
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TopicListener::class.java)
    }

    @KafkaListener(topics = ["\${topic.name.consumer}"], groupId = "group_id")
    fun consume(payload: ConsumerRecord<String?, String?>) {
        LOGGER.info("Topic: {}", topicName)
        LOGGER.info("key: {}", payload.key())
        LOGGER.info("Headers: {}", payload.headers())
        LOGGER.info("Partion: {}", payload.partition())
        LOGGER.info("Order: {}", payload.value())
    }

}