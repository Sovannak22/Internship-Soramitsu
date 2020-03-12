package com.vannak.tech.api_project.services

import com.vannak.tech.api_project.api.DTO.CreateUserDTO
import com.vannak.tech.api_project.api.DTO.UpdateUserDTO
import com.vannak.tech.api_project.api.DTO.UserDTO
import com.vannak.tech.api_project.domain.model.User
import com.vannak.tech.api_project.repository.RoleRepository
import com.vannak.tech.api_project.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import javax.validation.Valid

@Component
class UserService (
        @Autowired
        var userRepository: UserRepository,
        @Autowired
        var roleRepository: RoleRepository
){
    lateinit var pageable: Pageable

    fun retrieveAllUser(page: Int):ResponseEntity<Page<UserDTO>>{
        pageable = PageRequest.of(page,5)
        return ResponseEntity.ok(userRepository.findAll(pageable).map {
            it.toDTO()
        })
    }

    fun findUserById(id:Long):ResponseEntity<UserDTO>{
        var user = userRepository.findById(id).toDTO()
        return ResponseEntity.ok(user)
    }

    fun createUser(@Valid createUserDTO: CreateUserDTO):ResponseEntity<UserDTO>{
        var role = roleRepository.findById(createUserDTO.role)
        var user = userRepository.save(User.fromDTO(createUserDTO,role))
        return ResponseEntity.ok(user.toDTO())
    }

    fun deleteUser(id: Long):ResponseEntity<UserDTO>{
        var user = userRepository.deleteById(id)
        return ResponseEntity.ok(user.toDTO())
    }

    fun updateUser(id:Long,dto: UpdateUserDTO):ResponseEntity<UserDTO>{
        val user = userRepository.findById(id)
        val role = roleRepository.findById(dto.role)
        val savedUser = userRepository.save(User.fromDTO(dto,role,user))
        return ResponseEntity.ok(savedUser.toDTO())
    }

    fun findByRole(id:Long,page:Int):ResponseEntity<Page<UserDTO>>{
        pageable = PageRequest.of(page,5)
        val users = userRepository.findByRoleId(id,pageable)
        return  ResponseEntity.ok(
                users.map {
                    it.toDTO()
                }
        )
    }

    fun findByValue(query:String,id: Long,page: Int):ResponseEntity<Page<UserDTO>>{
        pageable = PageRequest.of(page,5)
        val users = userRepository.findByValue(query,id,pageable)
        return ResponseEntity.ok(
                users.map {
                    it.toDTO()
                }
        )
    }

}