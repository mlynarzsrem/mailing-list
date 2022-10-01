package com.jmlynarz.mailinglist.mailing

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*
import javax.mail.Flags
import javax.mail.Folder
import javax.mail.Session
import javax.mail.search.FlagTerm


@Service
class MailReadService(private val mailReceiveProperties: MailReceiveProperties) {

    @Scheduled(cron="0 */2 * * * *")
    fun readMails() {
        println("Read inbox")
        val emailSession = createSession()
        val store = emailSession.getStore("imaps")
        store.connect(mailReceiveProperties.host, mailReceiveProperties.username, mailReceiveProperties.password)

        val inbox = store.getFolder("Inbox")
        inbox.open(Folder.READ_WRITE)

        val messages = inbox.search(FlagTerm(Flags(Flags.Flag.SEEN), false))
        messages.forEach {
            println(it.subject)
            println(it.content)
        }
        inbox.close(false)
        store.close()
    }

    private fun createSession(): Session {
        val properties = Properties()
        properties["mail.imap.host"] = mailReceiveProperties.host
        properties["mail.imap.port"] = mailReceiveProperties.port
        properties["mail.imap.starttls.enable"] = mailReceiveProperties.starttls
        properties["mail.imap.ssl.trust"] = mailReceiveProperties.host

        return Session.getDefaultInstance(properties)
    }
}