package com.example

import io.ktor.server.application.Application


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.doMigration() //start hote sabse pehle migration check kiya aur niche databse connect kiya
    DatabaseFactory.connectToDatabase() // server start hote hi database se connect karwa diya

    configureSerialization()
    configureRouting()



}

