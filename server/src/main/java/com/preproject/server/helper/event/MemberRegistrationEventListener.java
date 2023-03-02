package com.preproject.server.helper.event;

import com.preproject.server.helper.email.EmailSender;
import com.preproject.server.member.Service.MemberService;
import com.preproject.server.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Configuration
@Component
@Slf4j
@RequiredArgsConstructor
public class MemberRegistrationEventListener {
    @Value("${mail.subject.member.registration}")
    private String subject;

    @Value("${mail.template.name.member.join}")
    private String templateName;

    private final EmailSender emailSender;
    private final MemberService memberService;

    @SneakyThrows
    @Async
    @EventListener
    public void listen(MemberRegistrationApplicationEvent event) {
        try {
            String[] to = new String[]{event.getMember().getEmail()};
            String message = event.getMember().getEmail() + "님, 똥빵구나먹어라! ";
            emailSender.sendEmail(to, subject, message, templateName);
        } catch (MailSendException e) {
            log.error("MailSendException L rollback for Member Registration :");
            e.printStackTrace();
            Member member = event.getMember();
            memberService.deleteMember(member.getId());
        }
    }
}
