package com.xavierbouclet.kafkademo

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import reactor.kafka.sender.SenderOptions


@Configuration
class ReactiveKafkaProducerConfig {
    @Bean
    fun reactiveKafkaProducerTemplate(properties: KafkaProperties) =
        ReactiveKafkaProducerTemplate<String, String>(SenderOptions.create(properties.buildProducerProperties()))

    @Bean
    fun reactiveKafkaProducerAvroTemplate(properties: KafkaProperties) =
        ReactiveKafkaProducerTemplate<String, com.xavierbouclet.kafkademo.avro.Message>(SenderOptions.create(properties.buildProducerProperties()))

}