package com.vannak.tech.api_project.domain.model

import com.vannak.tech.api_project.api.DTO.CreateUserDTO
import com.vannak.tech.api_project.api.DTO.UpdateUserDTO
import com.vannak.tech.api_project.api.DTO.UserDTO
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        @Column(name="id")
        var id:Long=0,
        @Column(name = "name")
        var name:String,
        @Column(name = "dob")
        var dob:Date,
        @Column(name = "phone_number")
        var phoneNumber: String,
        @Column(name = "email")
        var email:String,
        @Column(name = "created_at")
        var createdAt:Date,
        @Column(name = "updated_at")
        var updatedAt:Date,
        @ManyToOne(fetch = FetchType.LAZY)
        var role:Role?
){

    fun toDTO(): UserDTO = UserDTO(
            id = id,
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            dob = dob.toString(),
            role = role?.id,
            createdAt = createdAt.toString(),
            updatedAt = updatedAt.toString()
    )



    companion object{
        fun fromDTO(dto:CreateUserDTO, role: Role?): User{
            return User(
                    name = dto.name,
                    email = dto.email,
                    phoneNumber = dto.email,
                    dob = dto.dob,
                    role = role,
                    createdAt = Date(),
                    updatedAt = Date()
            )
        }

        fun fromDTO(dto: UpdateUserDTO, role: Role?, oriUser: User): User{
            return User(
                    id = oriUser.id,
                    name = dto.name ?: oriUser.name,
                    phoneNumber = dto.phoneNumber?:oriUser.phoneNumber,
                    dob = dto.dob?:oriUser.dob,
                    role = role?:oriUser.role,
                    email = dto.email?:oriUser.email,
                    createdAt = oriUser.createdAt,
                    updatedAt = Date()
            )
        }
    }

}