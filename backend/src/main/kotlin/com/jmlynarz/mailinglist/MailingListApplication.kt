package com.jmlynarz.mailinglist

import com.jmlynarz.mailinglist.config.DefaultAdminUserProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(DefaultAdminUserProperties::class)
class MailingListApplication

fun main(args: Array<String>) {
	runApplication<MailingListApplication>(*args)
}
