package com.jmlynarz.mailinglist.subscribers

import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable
import java.util.UUID

@Repository
interface SubscribersRepository: PagingAndSortingRepository<Subscriber, UUID> {

    @Query("SELECT s FROM Subscriber s WHERE :query = '' OR s.email LIKE %:query% OR s.name LIKE %:query% OR s.surname LIKE %:query%")
    fun searchSubscribers(query: String, pageable: Pageable): Page<Subscriber>
}