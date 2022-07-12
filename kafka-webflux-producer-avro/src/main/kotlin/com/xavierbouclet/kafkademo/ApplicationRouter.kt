package com.xavierbouclet.kafkademo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class ApplicationRouter(val messageHandler: MessageHandler) {

    @Bean
    fun route() = router {
        "/kafka".nest {
            "/send".nest {
                GET("", messageHandler::send)
            }
            "/json".nest {
                POST("", messageHandler::sendJson)
            }
        }
    }
}

