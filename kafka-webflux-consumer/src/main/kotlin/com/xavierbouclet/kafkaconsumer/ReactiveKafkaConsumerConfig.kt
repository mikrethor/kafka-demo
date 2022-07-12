package com.xavierbouclet.kafkaconsumer

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import reactor.kafka.receiver.ReceiverOptions
import java.util.*


@Configuration
class ReactiveKafkaConsumerConfig {

    @Bean
    fun kafkaReceiverOptions(@Value(value = "\${topic.name.consumer}") topic: String, kafkaProperties: KafkaProperties): ReceiverOptions<String?, Message> {
        val basicReceiverOptions: ReceiverOptions<String, Message> = ReceiverOptions.create(kafkaProperties.buildConsumerProperties())
        return basicReceiverOptions.subscription(Collections.singletonList(topic))
    }

    @Bean
    fun reactiveKafkaConsumerTemplate(kafkaReceiverOptions: ReceiverOptions<String, Message>) =
        ReactiveKafkaConsumerTemplate(kafkaReceiverOptions)

}