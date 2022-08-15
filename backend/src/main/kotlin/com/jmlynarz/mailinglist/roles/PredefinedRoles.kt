package com.jmlynarz.mailinglist.roles

import com.jmlynarz.mailinglist.resources.Resource

enum class PredefinedRoles(val accesses: List<RoleResourceAccess>) {
    ADMIN(listOf(
            RoleResourceAccess(Resource.USERS, readAccess = true, writeAccess = true),
            RoleResourceAccess(Resource.PUBLISHERS, readAccess = true, writeAccess = true),
            RoleResourceAccess(Resource.SUBSCRIBERS, readAccess = true, writeAccess = true),
            RoleResourceAccess(Resource.TOPICS, readAccess = true, writeAccess = true),
            RoleResourceAccess(Resource.ROLES, readAccess = true, writeAccess = true)
    ))
}