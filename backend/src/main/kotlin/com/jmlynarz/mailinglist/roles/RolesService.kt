package com.jmlynarz.mailinglist.roles

import com.jmlynarz.mailinglist.resources.ResourceAccessesRepository
import org.springframework.stereotype.Service

@Service
class RolesService(val rolesRepository: RolesRepository,
                   val resourceAccessesRepository: ResourceAccessesRepository) {

}