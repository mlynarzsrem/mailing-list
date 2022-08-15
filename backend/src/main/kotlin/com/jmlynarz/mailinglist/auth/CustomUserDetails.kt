package com.jmlynarz.mailinglist.auth

import com.jmlynarz.mailinglist.resources.ResourceAccess
import com.jmlynarz.mailinglist.users.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(private val user: User): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return extractAuthorities()
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    private fun extractAuthorities(): MutableCollection<CustomAuthority> {
        return user.roles.flatMap { it.accesses }
                .flatMap {resourceAccessToAuthorities(it) }.toMutableList()
    }

    private fun resourceAccessToAuthorities(resourceAccess: ResourceAccess): MutableCollection<CustomAuthority> {
        val authorities: MutableList<CustomAuthority> = mutableListOf()
        if(resourceAccess.readAccess) {
            authorities.add(CustomAuthority(resourceAccess.resource.name + "_READ"))
        }
        if(resourceAccess.writeAccess) {
            authorities.add(CustomAuthority(resourceAccess.resource.name + "_WRITE"))
        }

        return authorities
    }
}