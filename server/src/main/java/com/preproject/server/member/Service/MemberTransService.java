package com.preproject.server.member.Service;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.member.dto.MemberListDto;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.mapper.MemberMapper;
import com.preproject.server.question.entity.Question;
import com.preproject.server.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberTransService {
    private final static String MEMBER_DEFAULT_URL = "/users";
    private final MemberMapper memberMapper;
    private final MemberService memberService;

    public Page<MemberListDto> getMemberPageDto(Pageable pageable) {
        Page<Member> memberPage = memberService.getPageMember(pageable);
        return memberPage.map(this::getMemberListDto);

    }
    @Transactional
    public URI updateMember(Member member, Set<String> tags) {
        Member updatedMember = memberService.updatedMember(member, tags);

        return responseUrl(updatedMember);
    }
    @Transactional
    public URI createMember(Member member) {
        memberService.createMember(member);
        return responseUrl(member);
    }

    private URI responseUrl(Member updatedMember) {
        return UriCreator.createUri(MEMBER_DEFAULT_URL, updatedMember.getId());
    }



    //#### 내부적인 동작 메서드 #### //
    private MemberListDto getMemberListDto(Member member) {
        MemberListDto responseDto = memberMapper.memberToMemberListDto(member);
        List<String> collect = transTagMemberToString(member);
        responseDto.setTags(collect);
        long sum = getTotalVoteSum(member);
        responseDto.setVoteCount(sum);
        return responseDto;
    }

    private long getTotalVoteSum(Member member) {
        long sum = member.getQuestions().stream().mapToLong(Question::getVoteCount).sum();
        sum += member.getAnswers().stream().mapToLong(Answer::getVoteCount).sum();
        return sum;
    }

    private List<String> transTagMemberToString(Member member) {
        List<String> collect = member.getTagMembers().stream().map(tag -> tag.getTag().getName()).collect(Collectors.toList());
        return collect;
    }

}