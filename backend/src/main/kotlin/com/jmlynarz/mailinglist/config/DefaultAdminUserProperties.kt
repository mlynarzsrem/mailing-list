package com.jmlynarz.mailinglist.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "admin")
data class DefaultAdminUserProperties @ConstructorBinding constructor(val email: String, val password: String)
