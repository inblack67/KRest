package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object TUser: IntIdTable(){
    val name = varchar("name", 30)
    val email = text("email").uniqueIndex()
    val username = varchar("username", 30).uniqueIndex()
    val password = varchar("password", 30)
}

class User(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<User>(TUser)
    var name by TUser.name
    var email by TUser.email
    var username by TUser.username
    var password by TUser.password
}

@Serializable
data class DUser(
    var id: Int? = null,
    val name: String,
    val email: String,
    val username: String,
    val password: String
)