package com.vannak.tech.api_project.repository

import com.vannak.tech.api_project.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepository: JpaRepository<User,Int> {

    @Query("SELECT u FROM User u where (u.name LIKE %:query% OR "+
            "u.email LIKE %:query% OR "+
            "u.phoneNumber LIKE %:query%) AND "+
            "u.id = :id")
    fun findByValue(@Param("query") query: String, @Param("id") id: Long, pageable: Pageable):Page<User>

    fun findById(id:Long):User

    fun deleteById(id:Long):User

    @Query("SELECT u FROM User u WHERE u.role = :role")
    fun findByRoleId(@Param("role") role: Long, pageable:Pageable):Page<User>
}