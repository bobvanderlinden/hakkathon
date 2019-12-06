import com.sun.xml.internal.ws.api.pipe.ContentType
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: List<String>) {
    embeddedServer(Netty, 8080) {
        routing {
            get("/event") {

            }
        }
    }.start(wait = true)
}