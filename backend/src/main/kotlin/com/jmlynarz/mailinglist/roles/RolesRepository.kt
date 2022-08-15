package com.jmlynarz.mailinglist.roles

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RolesRepository: PagingAndSortingRepository<Role, UUID> {

    fun findAllByName(name: String): List<Role>

    @Query("SELECT r FROM Role r where r.name in :names")
    fun findAllByNames(names: List<String>): List<Role>
}