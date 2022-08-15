package com.jmlynarz.mailinglist.publishers

import com.jmlynarz.mailinglist.topics.Topic
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
data class Publisher(
        @Id
        @GeneratedValue
        val id: UUID? = null,
        @Column(nullable = false, unique = true)
        val email: String,
        @OneToMany(cascade = [CascadeType.MERGE])
        var allowedTopics: List<Topic> = mutableListOf(),
        @Column(nullable = false)
        val created: Instant = Instant.now(),
        @UpdateTimestamp
        var updated : Instant? = null
)
