package com.xavierbouclet.kafkademo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaWebfluxAvroProducerApplication

fun main(args: Array<String>) {
    runApplication<KafkaWebfluxAvroProducerApplication>(*args)
}
