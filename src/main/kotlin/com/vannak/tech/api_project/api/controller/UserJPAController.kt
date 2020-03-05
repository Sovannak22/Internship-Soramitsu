package com.vannak.tech.api_project.api.controller

import com.vannak.tech.api_project.api.exception.IDNotFoundException
import com.vannak.tech.api_project.domain.model.User
import com.vannak.tech.api_project.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/jpa/users")
class UserJPAController(@Autowired var userRepository:UserRepository) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun retrieveAllUsers():List<User>{
        return userRepository.findAll()
    }

    @GetMapping("/{id}")
    fun retrieveUser(@PathVariable id:Int): Optional<User> {
        var user = userRepository.findById(id)
        logger.info("logging: ${user.toString()}")
        if (user== Optional.empty<User>())
            throw IDNotFoundException("$id")
        return user
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id:Int): Unit{
        userRepository.deleteById(id)
    }

    @PostMapping
    fun createUsers(@Valid @RequestBody user:User): ResponseEntity<User>{
        val savedUser = userRepository.save(user)

        return ResponseEntity.ok(savedUser)
    }

    @GetMapping("/search")
    fun findByValue(@RequestParam value:String): ResponseEntity<Optional<List<User>>>{
        val user = userRepository.findByValue(value)
        return ResponseEntity.ok(user)
    }


}