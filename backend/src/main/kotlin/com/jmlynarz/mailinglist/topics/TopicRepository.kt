package com.jmlynarz.mailinglist.topics

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TopicRepository: PagingAndSortingRepository<Topic, UUID> {

    @Query("SELECT t FROM Topic t WHERE t.name in :names")
    fun findAllByNames(names: List<String>): List<Topic>

    @Query("SELECT t FROM Topic t WHERE :query = '' OR t.name LIKE %:query%")
    fun findAllByQuery(query: String, pageable: Pageable): Page<Topic>
}