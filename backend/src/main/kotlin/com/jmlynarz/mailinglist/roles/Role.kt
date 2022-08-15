package com.jmlynarz.mailinglist.roles

import com.jmlynarz.mailinglist.resources.ResourceAccess
import java.util.*
import javax.persistence.*

@Entity
data class Role(
        @Id
        @GeneratedValue
        val id: UUID? = null,
        @Column(nullable = false, unique = true)
        val name: String,
        @OneToMany(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
        var accesses: List<ResourceAccess> = emptyList()
)
