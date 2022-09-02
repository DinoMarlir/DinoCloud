package de.dinomarlir.dinocloud.utils

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json

//https://github.com/mooziii/laboratory/blob/6d5d085a9c8e9dfeea959c7244eb8e3ef048b0e5/src/main/kotlin/me/obsilabor/laboratory/Values.kt#L14
val json = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
}

val httpClient = HttpClient {
    install(ContentNegotiation) {
        json(json)
    }
}

val scope = CoroutineScope(Dispatchers.IO)
val syncScope = CoroutineScope(Dispatchers.Default)