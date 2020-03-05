package com.vannak.tech.api_project.repository

import com.vannak.tech.api_project.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepository: JpaRepository<User,Int> {

//    @Query("SELECT u FROM User u WHERE u.name = :name")
//    fun findByName(@Param("name") name:String):Optional<List<User>>

    @Query("SELECT u FROM User u where u.name LIKE %:query% OR "+
            "u.email LIKE %:query% OR "+
            "u.phoneNumber LIKE %:query%")
    fun findByValue(@Param("query") query : String):Optional<List<User>>
}