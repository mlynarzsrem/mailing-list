package com.jmlynarz.mailinglist.auth

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue
        val id: UUID? = null,
        @Column(nullable = false, unique = true)
        val email: String,
        @Column(nullable = false)
        var password: String,
        @OneToMany(cascade = [CascadeType.ALL])
        var accesses: List<ResourceAccess> = emptyList()
)
