package com.jmlynarz.mailinglist.subscribers

import com.jmlynarz.mailinglist.topics.Topic
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.UUID
import javax.persistence.*

@Entity
data class Subscriber(
        @Id
        @GeneratedValue
        val id: UUID? = null,
        @Column(nullable = false, unique = true)
        val email: String,
        @ManyToMany(cascade = [CascadeType.MERGE], mappedBy = "subscribers")
        var topics: List<Topic> = mutableListOf(),
        var name: String? = null,
        var surname: String? = null,
        @Column(nullable = false)
        val created: Instant = Instant.now(),
        @UpdateTimestamp
        var updated : Instant? = null
)