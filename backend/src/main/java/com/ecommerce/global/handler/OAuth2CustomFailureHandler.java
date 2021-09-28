package com.ecommerce.global.handler;

import com.ecommerce.domain.auth.session.SessionUser;
import com.google.common.base.Charsets;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.apache.commons.codec.CharEncoding.UTF_8;

public class OAuth2CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
        final HttpSession session = request.getSession();
        final SessionUser user = (SessionUser) session.getAttribute(SessionUser.SESSION_KEY);
        final String redirectUrl = redirectUrl(user);
        response.setCharacterEncoding(MediaType.TEXT_PLAIN_VALUE);
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private String redirectUrl(final SessionUser user) {
        final String targetUrl = "http://localhost:8083/login";
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam(user.toString())
                .encode(Charsets.UTF_8)
                .build().toUriString();
    }

}
