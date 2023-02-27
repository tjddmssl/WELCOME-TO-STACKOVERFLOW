package com.preproject.server.auth.handler.oauth2;

import com.preproject.server.auth.JwtTokenizer;
import com.preproject.server.auth.dto.PrincipalDto;
import com.preproject.server.auth.utils.CustomAuthorityUtils;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class OAuth2MemberSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final MemberService memberService;
    private final ClientRegistration clientRegistration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = String.valueOf(oAuth2User.getAttributes().get("email"));
        String profile = String.valueOf(oAuth2User.getAttributes().get("picture"));
        String name = String.valueOf(oAuth2User.getAttributes().get("name"));
        String locale = String.valueOf(oAuth2User.getAttributes().get("locale"));



        List<String> authorities = authorityUtils.createRoles(email);
        //TODO 리다이렉션 여부 결정 우리가 직접할것인가 아니면 프런트가 해줄 것인가

        Member member = saveMember(email, profile, locale, name);


        redirect(request, response, email, authorities, member);
    }

    private Member saveMember(String email, String profile, String location, String displayName) {
        String provider = clientRegistration.getRegistrationId();
        String providerId = clientRegistration.getClientId();
        Member member = Member.builder()
                .profile(profile)
                .email(email)
                .password(UUID.randomUUID().toString())
                .location(location)
                .provider(provider)
                .displayName(displayName)
                .provideId(providerId)
                .emailAuth(true)   //이메일 인증을 할 필요가 없다.
                .build();
//        return memberService.createMember(member);
        return memberService.oAuth2CreateOrGet(member);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String username, List<String> authorities,Member member) throws IOException {
        String accessToken = delegateAccessToken(username, authorities,member);
        String refreshToken = delegateRefreshToken(username);

        String uri = createURI(accessToken, refreshToken).toString();

        //TODO BODY에 값을 적어넣는다.

        response.setHeader("Authorization","Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);
        log.info("#### response Header = {}", response.getHeaders("Authorization"));
        log.info("#### response Header = {}", response.getHeaders("Refresh"));
//        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    private String delegateAccessToken(String username, List<String> authorities,Member member) {
        PrincipalDto principalDto = PrincipalDto.builder().displayName(member.getDisplayName()).email(member.getEmail()).id(member.getId()).build();
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("username", username);
        claims.put("roles", authorities);
        claims.put("principal",principalDto);
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(String username) {
        String subject = username;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .path("/receive-token")
                .queryParams(queryParams)
                .build()
                .toUri();
    }

}
