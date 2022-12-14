package de.dinomarlir.dinocloud.terminal.implementation

import de.dinomarlir.dinocloud.terminal.Command
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*
import de.dinomarlir.dinocloud.terminal.CommandManager

//https://github.com/mooziii/mooziiis-utilities/blob/main/src/main/kotlin/me/obsilabor/util/terminal/commands/HelpCommand.kt
class HelpCommand : Command("help", "Prints help", listOf("?", "helpme", "supersecrethelpcommand")) {

    override fun handle(args: List<String>) {
        if(args.size == 1) {
            println(bold(brightWhite("COMMAND OVERVIEW")))
            println(" ")
            CommandManager.commands.forEach {
                println("${brightWhite(">")} ${gray(it.name)} ${white("-")} ${gray(it.description)}")
            }
            println(" ")
            println(italic(brightWhite("Type 'help <command>' for more information")))
        } else {
            val command = args[1]
            var found = false
            CommandManager.commands.forEach {
                if(it.name.equals(command, true) || it.aliases.contains(command.lowercase())) {
                    found = true
                    println(bold(brightWhite(it.name)))
                    println(" ")
                    println(brightWhite("Aliases:"))
                    println(italic(gray(it.aliases.joinToString(", "))))
                    println(" ")
                }
            }
            if(!found) {
                println(red("Command '${command}' not found try 'help' for help"))
            }
        }
    }
}