package com.vannak.tech.api_project.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long,
        var role:String
)