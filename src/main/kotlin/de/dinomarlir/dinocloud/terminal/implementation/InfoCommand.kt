package de.dinomarlir.dinocloud.terminal.implementation

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyles
import de.dinomarlir.dinocloud.terminal.Command
import de.dinomarlir.dinocloud.utils.httpClient
import de.dinomarlir.dinocloud.utils.scope
import de.dinomarlir.dinocloud.utils.syncScope
import de.dinomarlir.dinocloud.version
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL

class InfoCommand : Command("info", "Prints some infos of the cloud.", listOf("i")) {

    override fun handle(args: List<String>) {
            runBlocking {
                val gitVersion: String =
                    httpClient.get(URL("https://raw.githubusercontent.com/DinoMarlir/DinoCloud/master/.meta/version"))
                        .bodyAsText()
                println(TextStyles.bold(TextColors.white("      INFO:")))
                println(
                    TextStyles.bold(TextColors.brightBlue("VERSION: ")) + TextColors.gray(version) + " | " + TextColors.gray(
                        gitVersion
                    )
                )
                if (version == gitVersion) {
                    println(TextColors.brightGreen("Your version is up to date!"))
                } else {
                    println(TextStyles.bold(TextColors.red("You version isn't up to date!")))
            }
        }
    }
}