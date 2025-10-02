package my.id.tasius.dailysleeptracker.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json

/**
 * Builds a Ktor `HttpClient` prepared for either mock responses or the production API.
 *
 * @param mock set to `true` for deterministic fixtures via `MockEngine`; `false` to hit the remote host.
 * @return a configured client with JSON serialization, timeout guards, and a default HTTPS base URL.
 */
fun provideHttpClient(mock: Boolean = false): HttpClient = if (mock) {
    HttpClient(MockEngine) {
        install(ContentNegotiation) { json() }
        engine {
            addHandler { request ->
                when {
                    request.url.encodedPath == "/v1/sessions" && request.method.value == "GET" -> {
                        respond(
                            content = """{"data":[${exampleSessionJson()}]}""",
                            headers = headersOf("Content-Type" to listOf("application/json"))
                        )
                    }
                    request.url.encodedPath == "/v1/sessions" && request.method.value == "POST" -> {
                        respond("""{"data": ${exampleSessionJson()}}""",
                            headers = headersOf("Content-Type" to listOf("application/json")))
                    }
                    request.url.encodedPath.startsWith("/v1/goals") && request.method.value == "GET" -> {
                        respond("""{"data":{"targetBedtime":"22:30","targetWakeTime":"06:30","minHoursPerNight":7.5}}""",
                            headers = headersOf("Content-Type" to listOf("application/json")))
                    }
                    else -> respond("""{"error":"Not Found"}""", HttpStatusCode.NotFound)
                }
            }
        }
    }
} else {
    HttpClient {
        install(ContentNegotiation) { json() }
        install(HttpTimeout) { requestTimeoutMillis = 15_000 }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.sleep.local" // later: real domain
            }
        }
    }
}

/**
 * Supplies the canned session document used by the mock engine to mimic a successful API response.
 */
private fun exampleSessionJson(): String = """
  {
    "id":"sess-1",
    "date":"2025-09-28",
    "start":"2025-09-27T23:10:00Z",
    "end":"2025-09-28T06:30:00Z",
    "isNap":false,
    "awakenings":1,
    "sleepQuality":4,
    "tags":["coffee","late-night"],
    "notes":"Slept late after coding"
  }
""".trimIndent()
