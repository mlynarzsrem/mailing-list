package com.jmlynarz.mailinglist.mailing

import com.jmlynarz.mailinglist.subscribers.SubscribersRepository
import com.jmlynarz.mailinglist.topics.TopicRepository
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import javax.mail.Message
import javax.mail.internet.MimeMultipart

@Service
class MailSenderService(private val javaMailSender: JavaMailSender,
                        private val topicRepository: TopicRepository,
                        private val subscribersRepository: SubscribersRepository) {

    fun sendMassEmail(message: Message) {
        val helper = parseContent(message)

        helper.setSubject(message.subject)
        helper.setFrom("abc@gmail.com")
        helper.setBcc("mlynarzsrem@gmail.com")

        javaMailSender.send(helper.mimeMessage)
    }

    private fun parseContent(message: Message): MimeMessageHelper {
        return when(val content = message.content) {
            is String -> stringToMimeMessageHelper(content)
            is MimeMultipart -> mimeMultipartToMimeMessageHelper(content)
            else -> stringToMimeMessageHelper(content.toString())
        }
    }

    private fun stringToMimeMessageHelper(message: String) : MimeMessageHelper {
        val mimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(mimeMessage, "utf-8")
        helper.setText(message, true)

        return helper
    }

    private fun mimeMultipartToMimeMessageHelper(message: MimeMultipart) : MimeMessageHelper {
        val mimeMessage = javaMailSender.createMimeMessage()
        mimeMessage.setContent(message)
        return MimeMessageHelper(mimeMessage, "utf-8")
    }

}