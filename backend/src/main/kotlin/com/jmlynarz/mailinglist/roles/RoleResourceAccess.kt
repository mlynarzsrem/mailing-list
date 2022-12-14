package com.jmlynarz.mailinglist.roles

import com.jmlynarz.mailinglist.resources.Resource

data class RoleResourceAccess(
        val resource: Resource,
        val readAccess: Boolean = false,
        val writeAccess: Boolean = false
)
