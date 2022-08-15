package com.jmlynarz.mailinglist.auth

import com.jmlynarz.mailinglist.users.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findAllByEmail(username!!).firstOrNull()?.let { CustomUserDetails(it) }!!
    }
}