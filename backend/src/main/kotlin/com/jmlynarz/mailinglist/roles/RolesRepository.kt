package com.jmlynarz.mailinglist.roles

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RolesRepository: PagingAndSortingRepository<Role, UUID> {

    fun findAllByName(name: String): List<Role>

    @Query("SELECT r FROM Role r WHERE :query = '' OR r.name LIKE %:query%")
    fun findAllByQuery(query: String, pageable: Pageable): Page<Role>

    @Query("SELECT r FROM Role r WHERE r.name in :names")
    fun findAllByNames(names: List<String>): List<Role>
}