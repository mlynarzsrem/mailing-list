package com.jmlynarz.mailinglist.users

import com.jmlynarz.mailinglist.config.DefaultAdminUserProperties
import com.jmlynarz.mailinglist.roles.PredefinedRoles
import com.jmlynarz.mailinglist.roles.Role
import com.jmlynarz.mailinglist.roles.RolesRepository
import org.springframework.context.annotation.DependsOn
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
@DependsOn("predefinedRolesSetupComponent")
class InitAdminUserComponent(
        val userRepository: UserRepository,
        val defaultAdminUserProperties: DefaultAdminUserProperties,
        val rolesRepository: RolesRepository,
        val passwordEncoder: PasswordEncoder) {

    @PostConstruct
    fun init() {
        if (userRepository.findAllByEmail(defaultAdminUserProperties.email).isEmpty()) {
            val role: Role = rolesRepository.findAllByName(PredefinedRoles.ADMIN.name).first()
            userRepository.save(User(
                    email = defaultAdminUserProperties.email,
                    password = passwordEncoder.encode(defaultAdminUserProperties.password),
                    roles = listOf(role)
                )
            )
        }
    }

}