package com.jmlynarz.mailinglist.auth

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RestAuthenticationFailureHandler: SimpleUrlAuthenticationFailureHandler() {
    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, authenticationException: AuthenticationException) {
        super.onAuthenticationFailure(request, response, authenticationException)
    }
}