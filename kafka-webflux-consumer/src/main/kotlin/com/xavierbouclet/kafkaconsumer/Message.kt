package com.xavierbouclet.kafkaconsumer

import java.util.*

data class Message(val id: UUID?=null, val message: String="")