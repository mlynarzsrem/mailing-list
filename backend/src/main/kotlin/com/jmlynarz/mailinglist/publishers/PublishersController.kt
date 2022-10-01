package com.jmlynarz.mailinglist.publishers

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/publishers")
class PublishersController(private val publishersService: PublishersService) {

    @GetMapping
    @PreAuthorize("hasAuthority('PUBLISHERS_READ')")
    fun getPublisher(
            @RequestParam(required = false, defaultValue = "") query: String,
            pageable: Pageable
    ): Page<Publisher> {
        return publishersService.getPublishers(query, pageable)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PUBLISHERS_WRITE')")
    fun createPublisher(@RequestBody createPublisherRequest: CreatePublisherRequest): Publisher {
        return publishersService.createPublisher(createPublisherRequest)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PUBLISHERS_WRITE')")
    fun deletePublisher(@PathVariable id: UUID) {
        publishersService.removePublisher(id)
    }

    @PutMapping("/{id}/allowed-topics")
    @PreAuthorize("hasAuthority('PUBLISHERS_WRITE')")
    fun updateTopics(@PathVariable id: UUID, @RequestBody topics: List<String>) {
        publishersService.updateTopics(id, topics)
    }
}