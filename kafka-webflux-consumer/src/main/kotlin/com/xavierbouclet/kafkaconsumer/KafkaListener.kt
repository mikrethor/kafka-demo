package com.xavierbouclet.kafkaconsumer

import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component


@Component
class KafkaListener(val topicListener: TopicListener) {

    @EventListener(ContextRefreshedEvent::class)
    fun contextRefreshedEvent() {
       topicListener.consumeTopic().subscribe()
    }
}