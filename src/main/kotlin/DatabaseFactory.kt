package com.example

import org.jetbrains.exposed.sql.Database
import org.flywaydb.core.Flyway

object DatabaseFactory {

    // Database credentials as variables
//    private val jdbcUrl = System.getenv("DATABASE_URL") ?: "jdbc:postgresql://localhost:5432/car_tax_app_db"
    private val dbHost = System.getenv("DB_HOST") ?: "localhost"
    private val dbUser = System.getenv("DB_USER") ?: "mohdparvej"
    private val dbPassword = System.getenv("DB_PASSWORD") ?: ""
    private val dbName = System.getenv("DB_NAME") ?: "car_tax_app_db"


    private val rawDatabaseUrl = System.getenv("DATABASE_URL")
        ?: "postgresql://$dbHost:5432/$dbName"

    private val jdbcUrl = rawDatabaseUrl.replace(
        "postgresql://",
        "jdbc:postgresql://"
    )


    fun doMigration() {
        val flyway = Flyway.configure()
            .dataSource(jdbcUrl, dbUser, dbPassword)
            .locations("classpath:db/migration")
            .load()


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
