package com.vannak.tech.api_project.api.controller

import com.vannak.tech.api_project.api.exception.IDNotFoundException
import com.vannak.tech.api_project.domain.model.Role
import com.vannak.tech.api_project.domain.model.User
import com.vannak.tech.api_project.repository.RoleRepository
import com.vannak.tech.api_project.repository.UserRepository
import org.slf4j.LoggerFactory
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
        @Autowired var userRepository:UserRepository,
        @Autowired var roleRepository: RoleRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)
    lateinit var pageable: Pageable
    @GetMapping
    fun retrieveAllUsers():List<User>{
        return userRepository.findAll()
    }

    @GetMapping("/{id}")
    fun retrieveUser(@PathVariable id:Int): Optional<User> {
        var user = userRepository.findById(id)
        if (user== Optional.empty<User>())
            throw IDNotFoundException("$id")
        return user
    }

    //Find user by Role
    @GetMapping("/role/{id}")
    fun findByRole(@PathVariable id:Int,@RequestParam page:Int=0): ResponseEntity<Page<User>> {
        var role = roleRepository.findById(id)
        pageable = PageRequest.of(page,5)
        if (role == Optional.empty<Role>())
            throw IDNotFoundException("can not find role $id")
        var users = userRepository.findByRoleId(role,pageable)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/search")
    fun findByValue(@RequestParam(name = "value") value:String, @RequestParam id:Int): ResponseEntity<Optional<List<User>>>{
        val user = userRepository.findByValue(value,id)
        return ResponseEntity.ok(user)
    }


    @PostMapping
    fun createUsers(@Valid @RequestBody user:User): ResponseEntity<User>{
        val savedUser = userRepository.save(user)
        return ResponseEntity.ok(savedUser)
    }


    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id:Int): Unit{
        userRepository.deleteById(id)
    }




}