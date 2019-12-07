package io.github.bobvanderlinden

import handleEvent
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.request.header
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import models.Event

@kotlin.jvm.JvmOverloads
@Suppress("unused") // Referenced in application.conf
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson()
    }

    routing {
        get("/") {
            call.respondText("OK")
        }
        post("/") {
            val event = call.request.header("x-github-event")!!
            val payload = call.receive<Event>()
            handleEvent(event, payload)
        }
    }
}
