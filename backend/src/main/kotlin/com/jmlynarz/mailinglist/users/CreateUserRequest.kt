package com.jmlynarz.mailinglist.users

data class CreateUserRequest(
        val email: String,
        val password: String,
        val roles: List<String> = emptyList()
)
