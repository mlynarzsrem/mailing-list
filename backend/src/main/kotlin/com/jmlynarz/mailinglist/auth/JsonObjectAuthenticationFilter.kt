package com.jmlynarz.mailinglist.auth

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JsonObjectAuthenticationFilter: UsernamePasswordAuthenticationFilter() {
    private val objectMapper = ObjectMapper()

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val requestBody = IOUtils.toString(request.reader)
        val loginRequest: LoginRequest = objectMapper.readValue(requestBody, LoginRequest::class.java)
        val token = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        setDetails(request, token)

        return this.authenticationManager.authenticate(token)
    }
}