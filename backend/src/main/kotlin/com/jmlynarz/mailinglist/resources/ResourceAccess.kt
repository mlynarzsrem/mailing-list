package com.jmlynarz.mailinglist.resources

import java.util.*
import javax.persistence.*

@Entity
data class ResourceAccess(
        @Id
        @GeneratedValue
        val id: UUID? = null,
        @Enumerated(EnumType.STRING)
        val resource: Resource,
        var readAccess: Boolean,
        var writeAccess: Boolean
)
