package com.xavierbouclet.kafkademo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["kafka"])
class KafkaController(val topicProducer: TopicProducer) {
    @GetMapping(value = ["send"])
    fun send(@RequestParam(name = "message") message: String?) {
        topicProducer.send(message ?: "Test message sent to topic")
    }
}