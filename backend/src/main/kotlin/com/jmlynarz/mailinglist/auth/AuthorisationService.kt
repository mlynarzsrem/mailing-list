package com.jmlynarz.mailinglist.auth

import com.jmlynarz.mailinglist.users.User
import com.jmlynarz.mailinglist.users.UserRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthorisationService(private val userRepository: UserRepository) {

    fun hasUserWriteAccess(userId: UUID): Boolean {
        val user: User = userRepository.findById(userId).orElseThrow()
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        return user.email == authentication.name ||
                authentication.authorities.map { it.authority }.contains("USERS_WRITE")
    }
}