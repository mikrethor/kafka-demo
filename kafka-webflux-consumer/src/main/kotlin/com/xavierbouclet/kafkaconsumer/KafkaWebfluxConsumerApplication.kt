package com.xavierbouclet.kafkaconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

@SpringBootApplication
class KafkaWebfluxConsumerApplication

fun main(args: Array<String>) {
    runApplication<KafkaWebfluxConsumerApplication>(*args)
}
