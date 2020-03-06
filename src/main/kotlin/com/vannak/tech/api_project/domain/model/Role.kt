package com.vannak.tech.api_project.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Role(id:Int, role:String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id=id

    private var role=role


    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private lateinit var users:List<User>;

    fun getId(): Int{
        return this.id
    }

    fun getRole(): String{
        return this.role
    }

    fun getUsers():List<User>{
        return this.users
    }
}