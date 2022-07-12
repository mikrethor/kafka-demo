package com.xavierbouclet.kafkademo

import java.util.*

data class Message(val id: UUID?=null, val message: String="")