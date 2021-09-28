package com.ecommerce.global.config;

import com.ecommerce.domain.auth.oauth2.CustomOAuth2UserService;
import com.ecommerce.domain.user.domain.vo.UserRole;
import com.ecommerce.global.handler.OAuth2CustomFailureHandler;
import com.ecommerce.global.handler.OAuth2CustomSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.util.Arrays;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/statics/**")
                .antMatchers("/resources/**")
                .antMatchers("/css/**")
                .antMatchers("/vendor/**")
                .antMatchers("/js/**")
                .antMatchers("/favicon*/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/images/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/", "/index/**","/login/**", "/logout/**", "/signup/**").permitAll()
                .antMatchers("/test.html").hasAnyRole(allUserRole())
                .antMatchers("/api/v1/**").hasAnyRole(allUserRole())
                .anyRequest().authenticated();
        http.oauth2Login()
                .successHandler(oauth2CustomSuccessHandler())
                .failureHandler(oauth2CustomFailureHandler())
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }

    private String[] allUserRole() {
        return Arrays.stream(UserRole.values())
                .map(userRole -> userRole.name())
                .toArray(String[]::new);
    }

    @Bean
    OAuth2CustomSuccessHandler oauth2CustomSuccessHandler() {
        return new OAuth2CustomSuccessHandler();
    }

    @Bean
    OAuth2CustomFailureHandler oauth2CustomFailureHandler() {
        return new OAuth2CustomFailureHandler();
    }

}
