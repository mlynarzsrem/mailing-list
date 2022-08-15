package com.jmlynarz.mailinglist.users

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jmlynarz.mailinglist.roles.Role
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
        @OneToMany(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
        var roles: List<Role> = emptyList()
)
