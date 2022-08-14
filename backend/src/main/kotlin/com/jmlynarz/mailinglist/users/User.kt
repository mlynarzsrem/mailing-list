package com.jmlynarz.mailinglist.users

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jmlynarz.mailinglist.resources.ResourceAccess
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
        @JsonIgnore
        var password: String,
        @OneToMany(cascade = [CascadeType.ALL])
        var accesses: List<ResourceAccess> = emptyList()
)
