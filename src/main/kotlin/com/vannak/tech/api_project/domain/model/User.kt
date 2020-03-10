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

    private var name = name

    private var dob = dob

    private var phoneNumber=phoneNumber;

    private var email = email

    @ManyToOne(fetch = FetchType.LAZY)
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