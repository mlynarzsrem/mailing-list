package com.jmlynarz.mailinglist.roles

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RolesRepository: PagingAndSortingRepository<Role, UUID> {

    fun findAllByName(name: String): List<Role>
}