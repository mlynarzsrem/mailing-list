package com.jmlynarz.mailinglist.users

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository: PagingAndSortingRepository<User, UUID> {
    fun findAllByEmail(email: String): List<User>

    @Query("SELECT u FROM User u WHERE :query = '' OR u.email LIKE %:query%")
    fun findAllByQuery(query: String, pageable: Pageable): Page<User>
}