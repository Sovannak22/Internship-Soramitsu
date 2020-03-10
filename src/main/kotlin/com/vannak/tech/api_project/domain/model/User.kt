package com.vannak.tech.api_project.domain.model

import com.vannak.tech.api_project.api.DTO.CreateUserDTO
import com.vannak.tech.api_project.api.DTO.UserDTO
import net.minidev.json.annotate.JsonIgnore
import org.aspectj.lang.annotation.RequiredTypes
import java.util.*
import javax.annotation.processing.Generated
import javax.persistence.*
import javax.validation.constraints.*
import kotlin.math.min

@Entity
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long=0,
        var name:String,
        var dob:Date,
        var phoneNumber: String,
        var email:String,
        @ManyToOne(fetch = FetchType.LAZY)
        var role:Role
){
    fun toDTO(): UserDTO = UserDTO(
            id = id,
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            dob = dob.toString(),
            role = role.id
    )

    companion object{
        fun fromDTO(dto:CreateUserDTO,role:Role): User{
            return User(
                    name = dto.name,
                    email = dto.email,
                    phoneNumber = dto.email,
                    dob = dto.dob,
                    role = role
            )
        }
    }

}