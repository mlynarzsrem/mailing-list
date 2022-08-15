package com.jmlynarz.mailinglist.roles

data class CreateRoleRequest(
        val name: String,
        val roleResourceAccesses: List<RoleResourceAccess>
)
