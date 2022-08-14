package com.jmlynarz.mailinglist.users

import com.jmlynarz.mailinglist.resources.ResourceAccessesRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsersService(val userRepository: UserRepository,
                   val resourceAccessesRepository: ResourceAccessesRepository,
                   val passwordEncoder: PasswordEncoder) {

    fun createUser(createUserRequest: CreateUserRequest): User {
        val user = User(
                email = createUserRequest.email,
                password = passwordEncoder.encode(createUserRequest.password))

        return userRepository.save(user)
    }
}