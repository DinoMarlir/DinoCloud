package de.dinomarlir.dinocloud.terminal.implementation

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyles
import de.dinomarlir.dinocloud.terminal.Command
import de.dinomarlir.dinocloud.utils.httpClient
import de.dinomarlir.dinocloud.utils.scope
import de.dinomarlir.dinocloud.version
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.content.*
import kotlinx.coroutines.launch
import java.net.URL
import java.time.format.TextStyle

class InfoCommand : Command("info", "Prints some infos of the cloud.", listOf("i")) {

    override fun handle(args: List<String>) {
        println(TextStyles.bold(TextColors.white("      INFO:")))
        println(TextStyles.bold(TextColors.brightBlue("VERSION: ")) + TextColors.gray(version))

        scope.launch {
            val gitVersion: String = httpClient.get(URL("https://raw.githubusercontent.com/DinoMarlir/DinoCloud/master/.meta/version")).bodyAsText()
            println(
                if (version == "404: Not Found") {
                    TextStyles.bold(TextColors.red("Can't fetch the latest version!"))
                } else {
                    TextColors.blue(gitVersion)
                }
            )
        }
    }
}