package com.example

import org.jetbrains.exposed.sql.Database
import org.flywaydb.core.Flyway

object DatabaseFactory {

    // Database credentials as variables
    private val jdbcUrl = System.getenv("DATABASE_URL") ?: "jdbc:postgresql://localhost:5432/car_tax_app_db"
    private val dbUser = System.getenv("DB_USER") ?: "mohdparvej"
    private val dbPassword = System.getenv("DB_PASSWORD") ?: ""


    fun doMigration() {
        val flyway = Flyway.configure()
            .dataSource(jdbcUrl, dbUser, dbPassword)
            .load()

//        println(Flyway.configure().locations.contentToString())

        flyway.migrate()
    }

    fun connectToDatabase() {
        Database.connect(
            url = jdbcUrl,
            driver = "org.postgresql.Driver",
            user = dbUser,
            password = dbPassword
        )
    }
}
