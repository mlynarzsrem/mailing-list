package com.jmlynarz.mailinglist.roles

import com.jmlynarz.mailinglist.resources.ResourceAccess
import com.jmlynarz.mailinglist.resources.ResourceAccessesRepository
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class PredefinedRolesSetupComponent(val rolesRepository: RolesRepository,
                                    val resourceAccessesRepository: ResourceAccessesRepository) {
    @PostConstruct
    private fun init() {
        PredefinedRoles.values().forEach {
            val nullableRole: Role? =  rolesRepository.findAllByName(it.name).firstOrNull()
            nullableRole?.let { role: Role -> updateExistingRole(role, it) } ?: createRole(it)
        }
    }

    private fun updateExistingRole(role: Role, predefinedRole: PredefinedRoles) {
        val predefinedRoleResourceAccessesMap = predefinedRole.accesses.associateBy { it.resource }
        val resourceAccessesMap = role.accesses.associateBy { it.resource }.toMutableMap()
        predefinedRoleResourceAccessesMap.entries.forEach{
            if (resourceAccessesMap.containsKey(it.key)) {
                val resourceAccess = resourceAccessesMap[it.key]
                resourceAccess?.readAccess = it.value.readAccess
                resourceAccess?.writeAccess = it.value.writeAccess
                resourceAccessesMap[it.key] = resourceAccess!!
            } else {
                resourceAccessesMap[it.key] = ResourceAccess(resource = it.key, readAccess = it.value.readAccess, writeAccess = it.value.writeAccess)
            }
        }
        role.accesses = resourceAccessesMap.values.map { resourceAccessesRepository.save(it) }.toList()
        rolesRepository.save(role)
    }

    private fun createRole(predefinedRole: PredefinedRoles) {
        val resourceAccesses: List<ResourceAccess> = predefinedRole.accesses
                .map { ResourceAccess(resource =  it.resource, readAccess = it.readAccess, writeAccess = it.writeAccess) }
                .map { resourceAccessesRepository.save(it) }
        rolesRepository.save(Role(name = predefinedRole.name, accesses = resourceAccesses))
    }
}