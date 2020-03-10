package com.vannak.tech.api_project.api.DTO

import java.util.*
import javax.validation.constraints.*
import kotlin.math.min

data class UserDTO (
        var id:Long,
        var email:String,
        var phoneNumber:String,
        var dob:String,
        var name:String,
        var role:Long
)

data class createUserDTO(
        @field:Email
        @field:NotEmpty
        var email:String,

        @field:NotEmpty
        @field:Pattern(regexp = "\\+855[0-9]{8}[0-9]?",message = "Phone number format is invalid")
        var phoneNumber: String,

        @field:NotEmpty
        @field:Past
        var dob:Date,

        @field:NotEmpty
        @field:Size(min=2)
        var name: String,

        @field:NotEmpty
        var role: Long
)