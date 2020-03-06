package com.vannak.tech.api_project.repository

import com.vannak.tech.api_project.domain.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role,Int>{

}