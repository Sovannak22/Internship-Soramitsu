package com.vannak.tech.api_project.domain.model

import net.minidev.json.annotate.JsonIgnore
import org.aspectj.lang.annotation.RequiredTypes
import java.util.*
import javax.annotation.processing.Generated
import javax.persistence.*
import javax.validation.constraints.*
import kotlin.math.min

@Entity
class User (id:Int,name:String,dob:Date,phoneNumber: String,email:String,role:Role){

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id = id
    @Size(min = 2, message = "Size must be 2")
    private var name = name
    @Past
    private var dob = dob

    @Pattern(regexp = "\\+855[0-9]{8}[0-9]?",message = "Phone number format is invalid")
    private var phoneNumber=phoneNumber;

    @Email
    private var email = email

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private var role=role


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
    fun getRole():Role{
        return this.role
    }

    fun setId(id:Int): Unit{
        this.id=id;
    }


}