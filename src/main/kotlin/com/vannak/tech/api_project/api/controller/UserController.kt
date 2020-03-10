package com.vannak.tech.api_project.api.controller

import com.vannak.tech.api_project.api.DTO.CreateUserDTO
import com.vannak.tech.api_project.api.DTO.UserDTO
import com.vannak.tech.api_project.api.exception.IDNotFoundException
import com.vannak.tech.api_project.domain.model.Role
import com.vannak.tech.api_project.domain.model.User
import com.vannak.tech.api_project.repository.RoleRepository
import com.vannak.tech.api_project.repository.UserRepository
import com.vannak.tech.api_project.services.UserService
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
        @Autowired var userService: UserService,
        @Autowired var userRepository:UserRepository,
        @Autowired var roleRepository: RoleRepository
) {
    lateinit var pageable: Pageable

    @GetMapping
    fun retrieveAllUsers():ResponseEntity<List<UserDTO>>{
        return userService.retrieveAllUser()
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id:Long): ResponseEntity<UserDTO> {
        return userService.findUserById(id)
    }

    //Find user by Role
//    @GetMapping("/role/{id}")
//    fun findByRole(@PathVariable id:Int,@RequestParam page:Int=0): ResponseEntity<Page<User>> {
//        var role = roleRepository.findById(id)
//        pageable = PageRequest.of(page,5)
//        if (role == Optional.empty<Role>())
//            throw IDNotFoundException("can not find role $id")
//        var users = userRepository.findByRoleId(role,pageable)
//        return ResponseEntity.ok(users)
//    }

//    @GetMapping("/search")
//    fun findByValue(@RequestParam(name = "value") value:String, @RequestParam id:Int): ResponseEntity<Optional<List<User>>>{
//        val user = userRepository.findByValue(value,id)
//        return ResponseEntity.ok(user)
//    }


    @PostMapping
    fun createUsers(@Valid @RequestBody userDTO: CreateUserDTO): ResponseEntity<Any>{
        return ResponseEntity.ok(userService.createUser(userDTO))
    }


    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id:Long): ResponseEntity<Any>{
        var user = userService.deleteUser(id)
        return ResponseEntity.ok(user)
    }




}