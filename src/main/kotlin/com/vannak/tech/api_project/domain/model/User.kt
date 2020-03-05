package com.vannak.tech.api_project.domain.model

import java.util.*
import javax.annotation.processing.Generated
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.Past
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import kotlin.math.min

@Entity
class User (id:Int,name:String,dob:Date,phoneNumber: String,email:String){

    @Id
    @GeneratedValue
    private var id:Int = id
    @Size(min = 2, message = "Size must be 2")
    private var name: String = name
    @Past
    private var dob: Date = dob

    @Pattern(regexp = "\\+855[0-9]{8}[0-9]?",message = "Phone number format is invalid")
    private var phoneNumber: String =phoneNumber;

    @Email
    private var email:String = email



    fun getId():Int{
        return this.id;
    }
    fun getName(): String{
        return this.name;
    }
    fun getDob(): Date{
        return this.dob;
    }
    fun getPhoneNumber(): String{
        return this.phoneNumber
    }
    fun getEmail():String{
        return this.email
    }

    fun setId(id:Int): Unit{
        this.id=id;
    }


}