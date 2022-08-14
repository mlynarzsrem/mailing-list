package com.jmlynarz.mailinglist.topics

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TopicsService(val topicRepository: TopicRepository) {

    fun getTopics(query: String, pageable: Pageable): Page<Topic> {
        return topicRepository.findAllByQuery(query.lowercase().trim(), pageable)
    }

    fun addTopic(name: String): Topic {
        return topicRepository.save(Topic(name= name))
    }

    fun deleteTopic(id: UUID) {
        topicRepository.deleteById(id)
    }
}