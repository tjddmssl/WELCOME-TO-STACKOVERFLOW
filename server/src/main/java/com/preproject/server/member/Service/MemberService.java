package com.preproject.server.member.Service;

import com.preproject.server.auth.utils.CustomAuthorityUtils;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.helper.event.MemberRegistrationApplicationEvent;
import com.preproject.server.member.data.MemberStatus;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.exception.MemberExceptionCode;
import com.preproject.server.member.repository.MemberRepository;
import com.preproject.server.tag.entity.Tag;
import com.preproject.server.tag.entity.TagMember;
import com.preproject.server.tag.exception.TagExceptionCode;
import com.preproject.server.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TagService tagService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private final ApplicationEventPublisher publisher;
    @Transactional
    public Member createMember(Member member) {
        log.info("member = {}", member);
        verifyExistsEmail(member.getEmail());
        setDefaultMemberInfo(member);
        Member save = memberRepository.save(member);
        publisher.publishEvent(new MemberRegistrationApplicationEvent(this,save));
        return save;
    }

    @Transactional
    public Member oAuth2CreateOrGet(Member member) {
        log.info("member = {}", member);

        Optional<Member> byEmail = memberRepository.findByEmail(member.getEmail());

        if (byEmail.isPresent()) {
            Member findMember = byEmail.get();
            switch (findMember.getMemberStatus()) {
                case MEMBER_ACTIVE:
                case MEMBER_SLEEP:
                    if (isProvider(member, findMember)) {
                        return findMember;
                    } else {
                        throw new BusinessLogicException(MemberExceptionCode.MEMBER_JWT_EXIST);

                    }
                case MEMBER_DELETE:
                    changeInfoMemberToOAuthMember(member, findMember); //값이 변경된다.
                    return findMember;
            }
        }
        setDefaultMemberInfo(member);


        return memberRepository.save(member);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new BusinessLogicException(MemberExceptionCode.MEMBER_NOT_FOUND));

        findMember.setMemberStatus(MemberStatus.MEMBER_DELETE);
    }

    public Member getMember(long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new BusinessLogicException(MemberExceptionCode.MEMBER_NOT_FOUND));
    }

    @Transactional
    public Member updatedMember(Member member, Set<String> tagMember) {
        Member findMember = checkMemberExist(member.getId());
        //검증 성공
        Optional.ofNullable(member.getPassword()).ifPresent(findMember::setPassword);
        Optional.ofNullable(member.getDisplayName()).ifPresent(findMember::setDisplayName);
        Optional.ofNullable(member.getProfile()).ifPresent(findMember::setProfile);
        Optional.ofNullable(member.getAboutMe()).ifPresent(findMember::setAboutMe);

        if (!tagMember.isEmpty()) {
            addTagMember(tagMember, findMember);
        }

        memberRepository.save(findMember);
        return findMember;
    }

    public Page<Member> getPageMember(Pageable pageable) {
        Page<Member> all = memberRepository.findAll(pageable);
        return all.isEmpty() ? null : all;
    }



    /*
     * 회원이 존재 하면 예외 발생
     * */
    private void verifyExistsEmail(String email) {
        if (memberRepository.findByEmailMemberActiveTmp(email).isPresent())
            throw new BusinessLogicException(MemberExceptionCode.MEMBER_EXIST);
    }


    /*
     * 회원이 없으명 예외 발생
     * */
    // 내부 동작 메서드 //
    public Member checkMemberExist(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new BusinessLogicException(MemberExceptionCode.MEMBER_NOT_FOUND));
    }

    private Member addTagMember(Set<String> tagMember, Member member) {
        List<Tag> tagList = tagService.findTagList();
        member.clearTagMember();

        tagMember.iterator().forEachRemaining(name -> {
            Tag tag = findTagFromTags(tagList, name);
            TagMember tmp = TagMember.builder().tag(tag).member(member).build();
            member.addTagMember(tmp);
        });

        return member;
    }

    private Tag findTagFromTags(List<Tag> tagList, String tagName) {
        for (Tag tag : tagList) {
            if (tag.getName().equals(tagName)) {
                return tag;
            }
        }
        throw new BusinessLogicException(TagExceptionCode.TAG_NOT_FOUND);
    }

    private void setDefaultMemberInfo(Member member) {
        String encryptedPassword = Optional.ofNullable(passwordEncoder.encode(member.getPassword())).get();
        member.setPassword(encryptedPassword);
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);
        log.info("member encryptedPassword = {}", encryptedPassword);
    }


    private static void changeInfoMemberToOAuthMember(Member member, Member findMember) {
        findMember.setProvider(member.getProvider());
        findMember.setProfile(member.getProfile());
        findMember.setLocation(member.getLocation());
        findMember.setPassword(member.getPassword());
        findMember.getTagMembers().clear();
        findMember.setAboutMe("");
        findMember.setMemberStatus(MemberStatus.MEMBER_ACTIVE);
    }
    private boolean isProvider(Member member, Member findMember) {
        return findMember.getProvider().equals(member.getProvider());
    }



}

