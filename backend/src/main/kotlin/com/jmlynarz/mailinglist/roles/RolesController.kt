package com.jmlynarz.mailinglist.roles

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/roles")
class RolesController(private val rolesService: RolesService) {

    @GetMapping
    @PreAuthorize("hasAuthority('ROLES_READ')")
    fun getRoles(@RequestParam(required = false, defaultValue = "") query: String,
                 pageable: Pageable): Page<Role> {
        return rolesService.getRoles(query, pageable)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLES_WRITE')")
    fun createRole(@RequestBody createRoleRequest: CreateRoleRequest): Role {
        return rolesService.createRole(createRoleRequest)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLES_WRITE')")
    fun updateRole(@PathVariable id: UUID, @RequestBody role: Role) {
        rolesService.updateRole(id, role)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLES_WRITE')")
    fun deleteRole(@PathVariable id: UUID) {
        rolesService.deleteRole(id)
    }
}