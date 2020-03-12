package com.vannak.tech.api_project.api.controller

import com.vannak.tech.api_project.api.DTO.CreateUserDTO
import com.vannak.tech.api_project.api.DTO.UpdateUserDTO
import com.vannak.tech.api_project.api.DTO.UserDTO
import com.vannak.tech.api_project.api.exception.IDNotFoundException
import com.vannak.tech.api_project.domain.model.Role
import com.vannak.tech.api_project.domain.model.User
import com.vannak.tech.api_project.repository.RoleRepository
import com.vannak.tech.api_project.repository.UserRepository
import com.vannak.tech.api_project.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
        @Autowired var userService: UserService
) {
    lateinit var pageable: Pageable

    @GetMapping
    fun retrieveAllUsers(@RequestParam(defaultValue = "0") page:Int=0):ResponseEntity<Any>{
        return ResponseEntity.ok(userService.retrieveAllUser(page))
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id:Long): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.findUserById(id))
    }

    //Find user by Role
    @GetMapping("/role/{id}")
    fun findByRole(@PathVariable id:Long,@RequestParam(defaultValue = "0") page:Int): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.findByRole(id,page))
    }

    @GetMapping("/search")
    fun findByValue(@RequestParam(name = "value") value:String, @RequestParam id:Long,@RequestParam page: Int): ResponseEntity<Any> {
        return ResponseEntity.ok(userService.findByValue(value,id,page))
    }


    @PostMapping
    fun createUsers(@Valid @RequestBody userDTO: CreateUserDTO): ResponseEntity<Any>{
        return ResponseEntity.ok(userService.createUser(userDTO))
    }

    @PatchMapping("/{id}")
    fun updateUser(@RequestBody dto: UpdateUserDTO,@PathVariable id:Long): ResponseEntity<Any>{
        return ResponseEntity.ok(userService.updateUser(id,dto))
    }


    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id:Long): ResponseEntity<Any>{
        return ResponseEntity.ok(userService.deleteUser(id))
    }




}