package com.xavierbouclet.kafkaconsumer

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class TopicListener(
    private val reactiveKafkaConsumerTemplate: ReactiveKafkaConsumerTemplate<String, com.xavierbouclet.kafkademo.avro.Message>
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(TopicListener::class.java)
    }

    fun consumeTopic(): Flux<com.xavierbouclet.kafkademo.avro.Message> {
        return reactiveKafkaConsumerTemplate
            .receiveAutoAck()
            .doOnNext {
                LOGGER.info(
                    "received key={}, value={} from topic={}, offset={}, partition={}, headers={}",
                    it.key(),
                    it.value(),
                    it.topic(),
                    it.offset(),
                    it.partition(),
                    it.headers()
                )
            }
            .map { it.value() }
            .doOnNext { LOGGER.info("successfully consumed {}={}", com.xavierbouclet.kafkademo.avro.Message::class.java.simpleName, it) }
            .doOnError { LOGGER.error("something bad happened while consuming : {}", it.message) }
    }

}