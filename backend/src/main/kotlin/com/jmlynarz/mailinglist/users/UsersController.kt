package com.jmlynarz.mailinglist.users

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UsersController(val usersService: UsersService) {

    @GetMapping
    @PreAuthorize("hasAuthority('USERS_READ')")
    fun getUsers(@RequestParam(required = false, defaultValue = "") query: String,
                  pageable: Pageable): Page<User> {
        return usersService.getUsers(query, pageable)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USERS_WRITE')")
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): User {
        return usersService.createUser(createUserRequest)
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasAuthority('USERS_WRITE')")
    fun assignRole(@PathVariable id: UUID, roles: List<String>) {
        usersService.assignRoles(id, roles)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USERS_WRITE')")
    fun deleteUser(@PathVariable id: UUID) {
        usersService.deleteUser(id)
    }

}