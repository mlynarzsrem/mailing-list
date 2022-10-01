package com.jmlynarz.mailinglist.mailing

import com.jmlynarz.mailinglist.common.Loggable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*
import javax.mail.*
import javax.mail.search.FlagTerm


@Service
class MailReadService(
        private val mailReceiveProperties: MailReceiveProperties,
        private val mailSenderService: MailSenderService): Loggable() {

    @Scheduled(cron="0 */1 * * * *")
    fun readMails() {
        log.info("Fetching new messages")
        val store = openImapConnection(createSession())
        val inbox = store.getFolder("Inbox")
        inbox.open(Folder.READ_WRITE)
        val messages = inbox.search(FlagTerm(Flags(Flags.Flag.SEEN), false))
        messages.forEach {
            log.info("Received message from ${it.from[0]} with subject: ${it.subject}")
            mailSenderService.sendMassEmail(it)
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

    private fun openImapConnection(session: Session): Store {
        val store = session.getStore("imaps")
        store.connect(mailReceiveProperties.host, mailReceiveProperties.username, mailReceiveProperties.password)

        return store
    }
}