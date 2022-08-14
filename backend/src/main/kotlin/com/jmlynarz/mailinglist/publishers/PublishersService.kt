package com.jmlynarz.mailinglist.publishers

import com.jmlynarz.mailinglist.topics.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class PublishersService(val publishersRepository: PublishersRepository,
                        val topicRepository: TopicRepository) {

    fun createPublisher(createPublisherRequest: CreatePublisherRequest): Publisher {
        val publisher = Publisher(
                email = createPublisherRequest.email,
                allowedTopics = topicRepository.findAllByNames(createPublisherRequest.allowedTopics)
        )

        return publishersRepository.save(publisher)
    }

    fun removePublisher(id: UUID) {
        publishersRepository.deleteById(id)
    }

    fun getPublishers(query: String, pageable: Pageable): Page<Publisher> {
        return publishersRepository.searchPublishers(query, pageable)
    }

    fun updateTopics(id: UUID, topics: List<String>) {
        val publisher = publishersRepository.findById(id)
                .orElseThrow()
        publisher.allowedTopics = topicRepository.findAllByNames(topics)
        publishersRepository.save(publisher)
    }
}