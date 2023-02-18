package com.preproject.server;

import com.preproject.server.Member.Service.DBMemberService;
import com.preproject.server.Member.Service.MemberService;
import com.preproject.server.Member.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/*")
//                .allowedOrigins("")
//                .allowedMethods("*");
//    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .formLogin()
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())//기본 핸들러
                .and()
                .exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl())//핸들러 추 후 변경 가능
//                .authenticationEntryPoint(new DelegatingAuthenticationEntryPoint())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .authorizeHttpRequests(authorize -> authorize
//                        .antMatchers("/question/**").hasRole("ADMIN")
//                        .antMatchers("/members/**").hasRole("USER")
                        .antMatchers("/**").permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public MemberService MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        return new DBMemberService(memberRepository,passwordEncoder);
    }
}
