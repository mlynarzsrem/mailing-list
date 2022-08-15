package com.jmlynarz.mailinglist.topics

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jmlynarz.mailinglist.subscribers.Subscriber
import java.util.UUID
import javax.persistence.*

@Entity
data class Topic(
        @Id
        @GeneratedValue
        val id: UUID? = null,
        @Column(nullable = false, unique = true)
        val name: String,
        @ManyToMany(cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
        @JoinTable(
                name = "topic_subscriber",
                joinColumns = [JoinColumn(name = "topic_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "subscriber_id", referencedColumnName = "id")])
        @JsonIgnore
        val subscribers: List<Subscriber> = mutableListOf(),
)