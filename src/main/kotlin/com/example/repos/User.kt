package com.example.repos

import com.example.models.DUser
import com.example.models.User
import org.jetbrains.exposed.sql.transactions.transaction

object RUser {
   fun add(input: DUser): Triple<Boolean, String, String?>{
       return try {
           transaction {
               User.new {
                   name = input.name
                   email = input.email
                   username = input.username
                   password = input.password
               }
           }
           Triple(true, "User Added", null)
       }catch (e: Exception){
           Triple(false, "", e.toString())
       }
   }
    fun getAll(): Triple<Boolean, List<DUser>?, String?>{
         return try {
             Triple(
                 true,
                 transaction { User.all().toList().map {
                 DUser(
                     id = it.id.toString().toInt(),
                     name = it.name,
                     email = it.email,
                     username = it.username,
                     password = it.password
                 )
             } }, null)
        } catch (e: Exception){
             Triple(false,null, e.toString())
        }
    }
}