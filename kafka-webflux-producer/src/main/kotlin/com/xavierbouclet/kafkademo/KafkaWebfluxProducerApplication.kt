package com.xavierbouclet.kafkademo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaWebfluxProducerApplication

fun main(args: Array<String>) {
    runApplication<KafkaWebfluxProducerApplication>(*args)
}
