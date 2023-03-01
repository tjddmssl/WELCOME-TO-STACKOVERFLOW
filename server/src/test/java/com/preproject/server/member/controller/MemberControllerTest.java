//package com.preproject.server.member.controller;
//
//import com.google.gson.Gson;
//import com.preproject.server.member.Service.MemberService;
//import com.preproject.server.member.dto.MemberPostDto;
//import com.preproject.server.member.mapper.MemberMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//@WebMvcTest(MemberController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureMockMvc
//class MemberControllerTest {
//    private final String URI = "/users";
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private MemberService memberService;
//    @MockBean
//    private MemberMapper mapper;
//    @Autowired
//    private Gson gson;
//
//    @Test
//    void createMember() {
//        //given
//        MemberPostDto post = new MemberPostDto("kk22@gmail.com", "1111", "응가뿡");
//        Long memberId = 1L;
//        String content = gson.toJson(post);
//        URI uri = UriComponentsBuilder.newInstance().path(URI).build().toUri();
//
//        //when
//
//        //then
//
//
//    }
//
//    @Test
//    void getMember() {
//        //given
//
//        //when
//
//        //then
//
//
//    }
//
//    @Test
//    void deleteMember() {
//        //given
//
//        //when
//
//        //then
//
//
//    }
//
//    @Test
//    void updateMember() {
//        //given
//
//        //when
//
//        //then
//
//
//    }
//
//    @Test
//    void getMemberList() {
//        //given
//
//        //when
//
//        //then
//
//    }
//}