package com.jmlynarz.mailinglist.mailing

import com.jmlynarz.mailinglist.subscribers.SubscribersRepository
import com.jmlynarz.mailinglist.topics.TopicRepository
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class MailSenderService(private val javaMailSender: JavaMailSender,
                        private val topicRepository: TopicRepository,
                        private val subscribersRepository: SubscribersRepository) {

    fun sendMassEmail(subject: String, message: String) {
        val mimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(mimeMessage, "utf-8")

        helper.setText(message, true)
        //helper.setTo()
        helper.setSubject(subject)
        helper.setFrom("abc@gmail.com")

        javaMailSender.send(mimeMessage)
    }
}