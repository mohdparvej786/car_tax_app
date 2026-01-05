package com.example

import org.jetbrains.exposed.sql.Database
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.callback.Callback
import kotlin.emptyArray

object DatabaseFactory {

    // Database credentials as variables
//    private val jdbcUrl = System.getenv("DATABASE_URL") ?: "jdbc:postgresql://localhost:5432/car_tax_app_db"
    private val dbHost = System.getenv("DB_HOST") ?: "localhost"
    private val dbUser = System.getenv("DB_USER") ?: "mohdparvej"
    private val dbPassword = System.getenv("DB_PASSWORD") ?: ""
    private val dbName = System.getenv("DB_NAME") ?: "car_tax_app_db"


    // ✅ FIXED: Proper JDBC URL conversion
    private val jdbcUrl: String by lazy {
        val envUrl = System.getenv("DATABASE_URL")
            ?: "postgresql://localhost:5432/car_tax_app_db"

        print("🔧 Original URL: $envUrl")

        // Convert to JDBC format
        val jdbcUrl = if (envUrl.startsWith("jdbc:postgresql://")) {
            envUrl
        } else if (envUrl.startsWith("postgresql://")) {
            "jdbc:$envUrl"
        } else {
            "jdbc:postgresql://$envUrl"
        }

        print("🔧 Converted to JDBC: $jdbcUrl")
        jdbcUrl
    }




    fun doMigration() {
         var flyway = Flyway.configure()
            .dataSource(jdbcUrl, dbUser, dbPassword)
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
