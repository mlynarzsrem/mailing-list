package com.jmlynarz.mailinglist.topics

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/topics")
class TopicsController(val topicsService: TopicsService) {

    @GetMapping
    @PreAuthorize("hasAuthority('TOPICS_READ')")
    fun getTopics(@RequestParam(required = false, defaultValue = "") query: String,
                  pageable: Pageable): Page<Topic> {
        return topicsService.getTopics(query, pageable)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('TOPICS_WRITE')")
    fun addTopic(@RequestBody name: String): Topic {
        return topicsService.addTopic(name)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TOPICS_WRITE')")
    fun deleteTopic(@PathVariable id: UUID) {
        topicsService.deleteTopic(id)
    }
}