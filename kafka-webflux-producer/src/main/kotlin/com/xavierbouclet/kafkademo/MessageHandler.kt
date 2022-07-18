package com.xavierbouclet.kafkademo

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class MessageHandler(private val producer: TopicProducer) {

    fun send(request: ServerRequest): Mono<ServerResponse> {
        val message = when {
            request.queryParam("message").isPresent -> {
                request.queryParam("message").get()
            }
            else -> "default message"
        }

        return producer.send(message).map { ServerResponse.ok().build() }.flatMap { it }
    }

    fun sendJson(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Message::class.java)
            .map { producer.send(it) }
            .flatMap { it }
            .map {
                ServerResponse
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .build()
            }.flatMap { it }
    }
}