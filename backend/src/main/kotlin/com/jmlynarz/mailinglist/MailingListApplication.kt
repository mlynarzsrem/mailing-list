package com.jmlynarz.mailinglist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MailingListApplication

fun main(args: Array<String>) {
	runApplication<MailingListApplication>(*args)
}
