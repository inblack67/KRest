package com.example.plugins

import com.example.models.DUser
import com.example.repos.RUser
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    routing {
        get("/") {
                call.respondText("Hello World!")
            }
        get("/users"){
            call.respond(RUser.getAll())
        }
        post("/users/add"){
            val input = call.receive<DUser>()
            call.respond(RUser.add(input))
        }
    }
}
