package com.jmlynarz.mailinglist

import com.jmlynarz.mailinglist.config.DefaultAdminUserProperties
import com.jmlynarz.mailinglist.mailing.MailReceiveProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableConfigurationProperties(DefaultAdminUserProperties::class, MailReceiveProperties::class)
@EnableScheduling
class MailingListApplication

fun main(args: Array<String>) {
	runApplication<MailingListApplication>(*args)
}
