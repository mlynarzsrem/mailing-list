package com.jmlynarz.mailinglist.roles

import com.jmlynarz.mailinglist.resources.ResourceAccess
import com.jmlynarz.mailinglist.resources.ResourceAccessesRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class RolesService(private val rolesRepository: RolesRepository,
                   private val resourceAccessesRepository: ResourceAccessesRepository) {


    fun getRoles(query: String, pageable: Pageable): Page<Role> {
        return rolesRepository.findAllByQuery(query, pageable)
    }

    fun createRole(createRoleRequest: CreateRoleRequest): Role {
        if (rolesRepository.findAllByName(createRoleRequest.name).isEmpty()) {
            val resourceAccesses: List<ResourceAccess> = createRoleRequest.roleResourceAccesses
                    .map { ResourceAccess(resource = it.resource, readAccess = it.readAccess, writeAccess = it.writeAccess) }
                    .map { resourceAccessesRepository.save(it) }
            return rolesRepository.save(Role(name = createRoleRequest.name, accesses = resourceAccesses, readonly = true))
        } else {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }

    fun updateRole(id: UUID, role: Role) {
        val existingRole = rolesRepository.findById(id).orElseThrow()
        existingRole.accesses = role.accesses.map { resourceAccessesRepository.save(it) }.toList()
        rolesRepository.save(existingRole)
    }

    fun deleteRole(id: UUID) {
        val role: Role = rolesRepository.findById(id).orElseThrow()
        if (role.readonly != true) {
            rolesRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.FORBIDDEN)
        }
    }
}