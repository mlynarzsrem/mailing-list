package com.jmlynarz.mailinglist.users

import com.jmlynarz.mailinglist.roles.RolesRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UsersService(val userRepository: UserRepository,
                   val rolesRepository: RolesRepository,
                   val passwordEncoder: PasswordEncoder) {

    fun getUsers(query: String, pageable: Pageable): Page<User> {
        return userRepository.findAllByQuery(query.lowercase().trim(), pageable)
    }

    fun createUser(createUserRequest: CreateUserRequest): User {
        val user = User(
                email = createUserRequest.email,
                password = passwordEncoder.encode(createUserRequest.password),
                roles = rolesRepository.findAllByNames(createUserRequest.roles)
        )

        return userRepository.save(user)
    }

    fun assignRoles(id: UUID, roles: List<String>) {
        val user = userRepository.findById(id).orElseThrow()
        user.roles = rolesRepository.findAllByNames(roles)
        userRepository.save(user)
    }

    fun deleteUser(id: UUID) {
        userRepository.deleteById(id)
    }
}