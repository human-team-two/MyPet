package com.example.team_pro_ex;

import com.example.team_pro_ex.com.Entity.business_Member.businessMember;
import com.example.team_pro_ex.com.persistence.business_Member.business_MemberRepository;
import com.example.team_pro_ex.com.persistence.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeamProExApplicationTests {
    //일반 회원
    @Autowired
    MemberRepository memberrepository;
    //사업자 회원
    @Autowired
    business_MemberRepository business_memberrepository;
    @Test
    @DisplayName("회원, 잘들어감?")
    void memberJoin() { //회원 회원가입
//        Member member = new Member();
//        member.setId("qudwls3771");
//        member.setPassword("11111");
//        member.setName("김병진");
//        member.setPhone_number("0103");
//        member.setAddress("집");
//        member.setYear("12");
//        member.setPet_D("12");
//        member.setPet_S("M");
//        member.setPet_T("개");
//        member.setPet_W(65);
//        member.setJoin_M("Y");
//        memberrepository.save(member);
//        System.out.println(member);
    }
    @Test
    @DisplayName("사업자, 잘들어감?")
    void business_MemberJoin(){//(사업자)회원 회원가입
        businessMember business_member = new businessMember();
        business_member.setId("김");
        business_member.setPassword("11");
        business_member.setBusiness_Number("11");
        business_member.setPhone_number("11");
        business_member.setStore_number("11");
        business_member.setStore_Name("11");
        business_member.setAddress("집");
        business_member.setJoin_M("Y");
        business_memberrepository.save(business_member);
        System.out.println( "사업자 잘 들어갔냐?" + business_member);

    }








}
