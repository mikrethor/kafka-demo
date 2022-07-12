package com.xavierbouclet.kafkaconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaWebfluxAvroConsumerApplication

fun main(args: Array<String>) {
    runApplication<KafkaWebfluxAvroConsumerApplication>(*args)
}
