package com.jmlynarz.mailinglist.subscribers.requests

data class CreateSubscriberRequest(
        val email: String,
        val name: String? = null,
        val surname: String? = null,
        val topics: List<String> = emptyList()
)
