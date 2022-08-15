package com.jmlynarz.mailinglist.users

data class ChangePasswordRequest(
        val oldPassword: String,
        val newPassword: String
)
