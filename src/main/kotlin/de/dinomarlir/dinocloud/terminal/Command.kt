package de.dinomarlir.dinocloud.terminal

abstract class Command(val name: String, val description: String, val aliases: List<String>) {

    abstract fun handle(args: List<String>)
}