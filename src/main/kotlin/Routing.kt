package com.example

import com.example.repository.DriverDetailRequest
import com.example.repository.DriverRepositoryDAO
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.Connection
import java.sql.DriverManager
import org.jetbrains.exposed.sql.*

fun Application.configureRouting() {
    routing {

        staticResources(
            remotePath = "/admin",
            basePackage = "static/admin"
        )

        get("/") {
            call.respondText("Hello World!")
        }

        post("/adddriver") {
            val driverdetail = call.receive<DriverDetailRequest>()
            val id = DriverRepositoryDAO.insertDriver(driverdetail)

            call.respond(driverdetail)
        }

        get("/drivers") {
            val drivers = DriverRepositoryDAO.getAllDrivers()
            call.respond(drivers)
        }

        delete("/drivers/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest)

            val deleted = DriverRepositoryDAO.deleteDriver(id)

            if (deleted) {
                call.respond(HttpStatusCode.OK, mapOf("status" to "deleted"))
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("status" to "not_found"))
            }
        }


    }
}
