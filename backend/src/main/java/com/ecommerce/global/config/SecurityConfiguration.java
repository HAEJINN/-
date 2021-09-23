package com.ecommerce.global.config;

import com.ecommerce.domain.auth.oauth2.CustomOAuth2UserService;
import com.ecommerce.domain.user.domain.vo.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }

    private String[] allUserRole() {
        return Arrays.stream(UserRole.values())
                .map(userRole -> userRole.name())
                .toArray(String[]::new);
    }

}
