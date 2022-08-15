package com.jmlynarz.mailinglist.auth

import org.springframework.security.core.GrantedAuthority

class CustomAuthority(private val name: String): GrantedAuthority {
    override fun getAuthority(): String {
        return name
    }
}