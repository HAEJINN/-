package com.ecommerce.global.handler;

import com.ecommerce.domain.auth.session.SessionUser;
import com.google.common.base.Charsets;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OAuth2CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
        final HttpSession session = request.getSession();
        final SessionUser user = (SessionUser) session.getAttribute(SessionUser.SESSION_KEY);
        final String redirectUrl = redirectUrl(user);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + redirectUrl);
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private String redirectUrl(final SessionUser user) {
        final String targetUrl = "http://localhost:8083/oauth2/redirect";
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam(user.toString())
                .encode(Charsets.UTF_8)
                .build().toUriString();
    }

}
