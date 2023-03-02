package com.preproject.server.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.preproject.server.auth.JwtTokenizer;
import com.preproject.server.auth.dto.LoginDto;
import com.preproject.server.auth.dto.LoginResponseDto;
import com.preproject.server.auth.dto.PrincipalDto;
import com.preproject.server.dto.ResponseDto;
import com.preproject.server.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
/*
* 클라이언트의 로그인 인증 정보 수신 -> AuthenticationManager 에 전달해 인증 처리 위임
* */
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("#### 검증 시작 ####");
        ObjectMapper objectMapper = new ObjectMapper();
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        //값을 찾아와 변경하는 작업이 필요하다.

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }//인증 권한 위임

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("##### 인증 성공 #####");
        Member member = (Member) authResult.getPrincipal();
        String accessToken = delegateAccessToken(member);
        String refreshToken = delegateRefreshToken(member);
        response.setHeader("Authorization","Bearer "+ accessToken);
        response.setHeader("Refresh",refreshToken);
        log.info("#### 토큰 준비 #####");


        LoginResponseDto build = LoginResponseDto.builder().memberId(member.getId()).displayName(member.getDisplayName()).build();
        ResponseDto responseDto = new ResponseDto<>(build);

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(responseDto);



        log.info("#### 응답을 위한 response 의 Member 목록 = {}",str);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(str);
        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
    }


    private String delegateAccessToken(Member member) {
        Map<String, Object> claims = new LinkedHashMap<>();
        PrincipalDto principal = PrincipalDto.builder().id(member.getId()).email(member.getEmail()).displayName(member.getDisplayName()).build();
        claims.put("username", member.getEmail());
        claims.put("roles", member.getRoles());
        claims.put("principal", principal);
        log.info("principal = {} ", principal);
        log.info("###### principal = {}", claims.get("principal").getClass());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(Member member) {
        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

}
