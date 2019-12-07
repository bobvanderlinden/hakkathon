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
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import models.Event

fun main() {
    embeddedServer(Netty, 3000) {
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
    }.start(wait = true)
}
