package com.vannak.tech.api_project.api.DTO

import java.util.*
import javax.validation.constraints.*

data class UserDTO(
        var id:Long,
        var email:String,
        var phoneNumber:String,
        var dob:String,
        var name:String,
        var role:Long,
        var createdAt:String,
        var updatedAt:String
)

data class CreateUserDTO(
        @field:Email
        @field:NotEmpty
        var email:String,

        @field:NotEmpty
        @field:Pattern(regexp = "\\+855[0-9]{8}[0-9]?",message = "Phone number format is invalid")
        var phoneNumber: String,

        @field:NotNull
        @field:Past
        var dob:Date,

        @field:NotEmpty
        @field:Size(min=2)
        var name: String,

        @field:NotNull
        var role: Long
)

data class UpdateUserDTO(
        @field:Email
        var email:String?,

        @field:Pattern(regexp = "\\+855[0-9]{8}[0-9]?",message = "Phone number format is invalid")
        var phoneNumber: String?,

        @field:Past
        var dob:Date?,

        @field:Size(min=2)
        var name: String?,

        var role: Long?
)