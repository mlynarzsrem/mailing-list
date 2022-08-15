package com.jmlynarz.mailinglist.subscribers

import com.jmlynarz.mailinglist.subscribers.requests.CreateSubscriberRequest
import com.jmlynarz.mailinglist.subscribers.requests.UpdateSubscriberRequest
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.UUID

@RestController
@RequestMapping("/subscribers")
class SubscribersController(val subscribersService: SubscribersService) {

    @GetMapping
    @PreAuthorize("hasAuthority('SUBSCRIBERS_READ')")
    fun getSubscribers(
            @RequestParam(required = false, defaultValue = "") query: String,
            pageable: Pageable
    ): Page<Subscriber> {
        return subscribersService.getSubscribers(query, pageable)
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SUBSCRIBERS_WRITE')")
    fun createSubscriber(@RequestBody createSubscriberRequest: CreateSubscriberRequest): Subscriber {
        return subscribersService.createSubscriber(createSubscriberRequest)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBSCRIBERS_WRITE')")
    fun deleteSubscriber(@PathVariable id: UUID) {
        subscribersService.removeSubscriber(id)
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBSCRIBERS_WRITE')")
    fun updateSubscriber(@PathVariable id: UUID, @RequestBody updateSubscriberRequest: UpdateSubscriberRequest) {
        subscribersService.updateSubscriber(id, updateSubscriberRequest)
    }

    @PutMapping("/{id}/topics")
    @PreAuthorize("hasAuthority('SUBSCRIBERS_WRITE')")
    fun updateTopics(@PathVariable id: UUID, @RequestBody topics: List<String>) {
        subscribersService.updateTopics(id, topics)
    }
}