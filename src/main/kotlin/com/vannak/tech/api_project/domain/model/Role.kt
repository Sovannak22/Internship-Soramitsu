package com.vannak.tech.api_project.domain.model

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        @Column(name = "id")
        var id:Long,
        @Column(name = "role")
        var role:String
)