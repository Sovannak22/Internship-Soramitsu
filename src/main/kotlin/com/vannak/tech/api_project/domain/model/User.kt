package com.vannak.tech.api_project.domain.model

import com.vannak.tech.api_project.api.DTO.CreateUserDTO
import com.vannak.tech.api_project.api.DTO.UpdateUserDTO
import com.vannak.tech.api_project.api.DTO.UserDTO
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
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
        @CreationTimestamp
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "updated_at")
        @UpdateTimestamp
        var updatedAt:LocalDateTime = LocalDateTime.now()
){
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    lateinit var role:Role

    fun toDTO(): UserDTO = UserDTO(
            id = id,
            name = name,
            email = email,
            phoneNumber = phoneNumber,
            dob = dob.toString(),
            role = role.id,
            createdAt = createdAt.toString(),
            updatedAt = updatedAt.toString()
    )



    companion object{
        fun fromDTO(dto:CreateUserDTO, role: Role?): User{
            var user = User(
                    name = dto.name,
                    email = dto.email,
                    phoneNumber = dto.email,
                    dob = dto.dob
            )
            user.role = Role(1,"Admin")
            return user

        }

        fun fromDTO(dto: UpdateUserDTO, role: Role?, oriUser: User): User{
            var user = User(
                    id = oriUser.id,
                    name = dto.name ?: oriUser.name,
                    phoneNumber = dto.phoneNumber?:oriUser.phoneNumber,
                    dob = dto.dob ?: oriUser.dob,
                    email = dto.email ?: oriUser.email,
                    createdAt = oriUser.createdAt
            )
            user.role = role ?: oriUser.role
            return user
        }
    }

}