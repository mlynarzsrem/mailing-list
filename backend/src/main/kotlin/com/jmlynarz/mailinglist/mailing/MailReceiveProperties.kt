package com.jmlynarz.mailinglist.mailing

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "mail-receive")
data class MailReceiveProperties @ConstructorBinding constructor(
        val host: String,
        val port: String,
        val starttls: String,
        val username: String,
        val password: String
)