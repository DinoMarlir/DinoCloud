package de.dinomarlir.dinocloud.terminal

//https://github.com/mooziii/mooziiis-utilities/blob/main/src/main/kotlin/me/obsilabor/util/terminal/CommandManager.kt
import com.github.ajalt.mordant.rendering.TextColors.*
import de.dinomarlir.dinocloud.terminal.implementation.HelpCommand
import de.dinomarlir.dinocloud.terminal.implementation.InfoCommand
import de.dinomarlir.dinocloud.version
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess

object CommandManager {

    var applicationName = "DinoCloud-$version"
    val PROMPT = "${brightWhite("${System.getProperty("user.name")}@$applicationName")} ${green(">")} "
    val helpCommand = HelpCommand()
    val commands = arrayListOf<Command>(
        helpCommand,
        InfoCommand()
    )
    private lateinit var readerThread: Thread
    private lateinit var reader: BufferedReader

    fun registerCommand(command: Command) {
        commands.add(command)
    }

    fun startReader() {
        println(brightYellow("CommandParser loaded successful, starting ThreadedCommandReader.."))
        reader = BufferedReader(InputStreamReader(System.`in`))
        readerThread = Thread {
            while (true) {
                print(PROMPT)
                var input = reader.readLine()
                input = input.replace(PROMPT, "")
                if (input.equals("exit", true)) {
                    println("Shutting down.")
                    stopReader()
                    exitProcess(0)
                } else {
                    var found = false
                    commands.forEach {
                        val args = input.split(" ")
                        if(it.name.equals(args[0], true) || it.aliases.contains(args[0].lowercase())) {
                            found = true
                            it.handle(args.toList())
                        }
                    }
                    if(!found) {
                        println(red("Command '${input.split(" ")[0]}' not found try '${helpCommand.name}' for help"))
                    }
                }
            }
        }
        readerThread.name = "ThreadedCommandReader"
        readerThread.start()
    }

    fun stopReader() {
        readerThread.interrupt()
        reader.close()
    }

}