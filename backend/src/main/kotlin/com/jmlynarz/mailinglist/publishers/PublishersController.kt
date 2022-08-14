package com.jmlynarz.mailinglist.publishers

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/publishers")
class PublishersController(val publishersService: PublishersService) {

    @GetMapping
    fun getPublisher(
            @RequestParam(required = false, defaultValue = "") query: String,
            pageable: Pageable
    ): Page<Publisher> {
        return publishersService.getPublishers(query, pageable)
    }

    @PostMapping
    fun createPublisher(@RequestBody createPublisherRequest: CreatePublisherRequest): Publisher {
        return publishersService.createPublisher(createPublisherRequest)
    }

    @DeleteMapping("/{id}")
    fun deletePublisher(@PathVariable id: UUID) {
        publishersService.removePublisher(id)
    }

    @PutMapping("/{id}/allowed-topics")
    fun updateTopics(@PathVariable id: UUID, @RequestBody topics: List<String>) {
        publishersService.updateTopics(id, topics)
    }
}