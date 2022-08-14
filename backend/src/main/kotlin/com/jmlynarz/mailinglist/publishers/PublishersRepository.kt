package com.jmlynarz.mailinglist.publishers

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PublishersRepository: PagingAndSortingRepository<Publisher, UUID> {

    @Query("SELECT p FROM Publisher p WHERE :query = '' OR p.email LIKE %:query%")
    fun searchPublishers(query: String, pageable: Pageable): Page<Publisher>
}