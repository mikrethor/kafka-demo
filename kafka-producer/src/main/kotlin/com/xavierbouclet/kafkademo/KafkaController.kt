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
        if (message == null) {
            topicProducer.send("Mensagem de teste enviada ao tópico")
        } else {
            topicProducer.send(message)
        }
    }
}