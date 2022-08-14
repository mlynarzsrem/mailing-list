package com.jmlynarz.mailinglist.subscribers

import com.jmlynarz.mailinglist.subscribers.requests.CreateSubscriberRequest
import com.jmlynarz.mailinglist.subscribers.requests.UpdateSubscriberRequest
import com.jmlynarz.mailinglist.topics.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SubscribersService(
        val subscribersRepository: SubscribersRepository,
        val topicRepository: TopicRepository) {

    fun createSubscriber(createSubscriberRequest: CreateSubscriberRequest): Subscriber {
        val subscriber = Subscriber(
                email = createSubscriberRequest.email,
                name = createSubscriberRequest.name,
                surname = createSubscriberRequest.surname,
                topics = topicRepository.findAllByNames(createSubscriberRequest.topics)
        )

        return subscribersRepository.save(subscriber)
    }

    fun removeSubscriber(id: UUID) {
        subscribersRepository.deleteById(id)
    }

    fun getSubscribers(query: String, pageable: Pageable): Page<Subscriber> {
        return subscribersRepository.searchSubscribers(query, pageable)
    }

    fun updateTopics(id: UUID, topics: List<String>) {
        val subscriber = subscribersRepository.findById(id)
                .orElseThrow()
        subscriber.topics = topicRepository.findAllByNames(topics)
        subscribersRepository.save(subscriber)
    }

    fun updateSubscriber(id: UUID, updateSubscriberRequest: UpdateSubscriberRequest) {
        val subscriber = subscribersRepository.findById(id)
                .orElseThrow()
        subscriber.surname = updateSubscriberRequest.surname
        subscriber.name = updateSubscriberRequest.name
        subscribersRepository.save(subscriber)
    }
}