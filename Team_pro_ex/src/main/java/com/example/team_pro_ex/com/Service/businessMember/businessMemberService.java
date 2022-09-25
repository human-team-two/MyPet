package com.example.team_pro_ex.com.Service.businessMember;

import com.example.team_pro_ex.com.Entity.business_Member.businessMember;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface businessMemberService {

    //회원목록
    List<businessMember> get_BusinessMemberList();


    List<businessMember> getBusinessListEncodingByBusinessMemberList(List<businessMember> businessMember);


    //회원가입
    void insertBusinessMember(businessMember businessmember);


    //myPage => 회원정보 수정할 때
    public businessMember getBusinessMemeber(businessMember businessmember);

    //회원정보 수정
    void updateBusinessMember(businessMember businessmember);


    //회원탈퇴
    void deleteUpdateBusinessMember(businessMember businessmember);

    //회원가입 유효성검사
    public Map<String, String> businessMember_Availabilty(Errors errors);


    //아이디 찾기
    public boolean booleanSearchUserById(businessMember businessmember);

    //아이디 찾기 => 아이디를 정확하게 입력해야 아이디를 찾을 수 있다. lepo에 like를 넣으면 찾을 수 있지만
    //그럴 수 없기 때문에 핸드폰 번호로 찾는다.
    businessMember getBusinessMemberWhereId(String id);




}
