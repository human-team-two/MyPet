package com.example.team_pro_ex.com.persistence.businessMember;

import com.example.team_pro_ex.com.Entity.business_Member.businessMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface businessMemberRepository extends JpaRepository<businessMember, Long> {


    //아이디 찾기 => 아이디가 완전히 일치해야함 =like로 해경가능하다 => 우리는 핸드폰번호로 찾을거기 때문에
    @Query(value = "select m from businessMember m where m.id = :id")
    businessMember findMemberById(String id);

    //아이디 찾기
    List<businessMember> findByIdContains(String id);

    //사업자 회원의 가입상태, 아이디, 탈퇴날짜를 제외한 모든 정보를 null처리를 한다.
    @Query(value = "update BUSINESS_MEMBER m set m.business_member_password = null, m.business_member_number = null," +
            " m.business_member_phone_number = null, m.business_member_join_M = 'N', business_member_Role = null, " +
            "m.join_D = null, m.join_O = to_date(SYSDATE, 'yyyy.mm.dd') " +
            "where m.business_Number_Seq = :business_Number_Seq", nativeQuery = true)
    businessMember updateDelete(Long business_Number_Seq);



}
