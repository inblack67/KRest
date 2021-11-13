package com.example

import com.example.models.TUser
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/postgres", driver = "org.postgresql.Driver",
        user = "postgres", password = "postgres")

    transaction {
        // print sql to std-out
        addLogger(StdOutSqlLogger)
        SchemaUtils.create (TUser)
    }
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
