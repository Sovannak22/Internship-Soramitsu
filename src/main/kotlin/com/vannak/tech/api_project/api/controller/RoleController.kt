package com.vannak.tech.api_project.api.controller

import com.vannak.tech.api_project.domain.model.Role
import com.vannak.tech.api_project.domain.model.User
import com.vannak.tech.api_project.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/role")
class RoleController(@Autowired var roleRepository: RoleRepository) {

    @PostMapping
    fun createRole(@RequestBody role:Role):ResponseEntity<Role>{
        var role = roleRepository.save(role)

        return ResponseEntity.ok(role)
    }

//    @GetMapping("/{id}/users")
//    fun getUsers(@PathVariable id:Int):List<User>{
//        var role = roleRepository.findById(id)
//        return role.get().getUsers()
//    }
}