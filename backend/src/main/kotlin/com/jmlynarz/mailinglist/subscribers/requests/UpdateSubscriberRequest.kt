package com.jmlynarz.mailinglist.subscribers.requests

data class UpdateSubscriberRequest(
        val name: String? = null,
        val surname: String? = null,
)
