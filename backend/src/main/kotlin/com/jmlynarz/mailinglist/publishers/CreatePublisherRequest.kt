package com.jmlynarz.mailinglist.publishers

data class CreatePublisherRequest(
        val email: String,
        val allowedTopics: List<String> = emptyList()
)
