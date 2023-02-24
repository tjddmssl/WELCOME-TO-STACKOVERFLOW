package com.preproject.server.member;

import com.preproject.server.member.entity.Member;
import com.preproject.server.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {

  private final MemberRepository memberRepository;

  public MemberRepositoryTest(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
}
