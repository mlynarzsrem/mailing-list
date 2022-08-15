package com.jmlynarz.mailinglist.roles

import com.jmlynarz.mailinglist.resources.Resource

enum class PredefinedRoles(val accesses: List<PredefinedRoleResourceAccess>) {
    ADMIN(listOf(
            PredefinedRoleResourceAccess(Resource.USERS, readAccess = true, writeAccess = true),
            PredefinedRoleResourceAccess(Resource.PUBLISHERS, readAccess = true, writeAccess = true),
            PredefinedRoleResourceAccess(Resource.SUBSCRIBERS, readAccess = true, writeAccess = true),
            PredefinedRoleResourceAccess(Resource.TOPICS, readAccess = true, writeAccess = true),
            PredefinedRoleResourceAccess(Resource.ROLES, readAccess = true, writeAccess = true)
    ))
}