package com.vannak.tech.api_project.domain.model

import com.vannak.tech.api_project.api.DTO.CreateUserDTO
import com.vannak.tech.api_project.api.DTO.UpdateUserDTO
import com.vannak.tech.api_project.api.DTO.UserDTO
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long=0,
        var name:String,
        var dob:Date,
        var phoneNumber: String,
        var email:String,
        @ManyToOne(fetch = FetchType.LAZY)
        var role: Role?
){

    fun toDTO(): UserDTO = UserDTO(
            id = id,
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            dob = dob.toString(),
            role = role?.id
    )



    companion object{
        fun fromDTO(dto:CreateUserDTO, role: Role?): User{
            return User(
                    name = dto.name,
                    email = dto.email,
                    phoneNumber = dto.email,
                    dob = dto.dob,
                    role = role
            )
        }

        fun fromDTO(dto: UpdateUserDTO, role: Role?, oriUser: User): User{
            var user = User(
                    id = oriUser.id,
                    name = dto.name ?: oriUser.name,
                    phoneNumber = dto.phoneNumber?:oriUser.phoneNumber,
                    dob = dto.dob?:oriUser.dob,
                    role = role?:oriUser.role,
                    email = dto.email?:oriUser.email
            )
            return user
        }

        fun toListDTO(users:List<User>):List<UserDTO>{
            var usersDTO = ArrayList<UserDTO>()
            for (user in users){
                usersDTO.add(user.toDTO())
            }
            return usersDTO
        }
    }

}