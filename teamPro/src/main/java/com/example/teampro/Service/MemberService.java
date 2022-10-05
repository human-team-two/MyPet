package com.example.teampro.Service;


import com.example.teampro.Entity.Member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;
import java.util.Map;

public interface MemberService {

    //회원가입
    public Member insertMember(Member member);

    //아이디 중복확인
    public void idCheck(Member member);

    public void UpdateMember(Member member);

    //회원탈퇴 = > 회원정보를 삭제하면 안되는 이유는 회원의 정보는 회사(우리)에게 돈이 되기 때문에
    //삭제하면 회사(우리) 입장에서는 손해다. 그래서 회원의 데이터를 회원 =아이디, 탈퇴날짜,가입상태
    //를 제외한 컬럼은 모두 null처리를 한다.
    //회원 탈퇴를 클릭할 시 = 아이디, 탈퇴날짜, 가입상태를 제외한 모든 컬럼이 null값으로 변경
    void deleteUpdateMember(Member member);

    //회원가입 유효성 검사
    public Map<String, String> member_Availability(Errors errors);

    //아이디 찾기
    public boolean booleanSearchUserById(Member member);

    //아이디 찾기 = 아이디를 입력해서 찾기 = 정확하게 아이디를 입력해야 한다.
    Member getMemberWhereId(String id);




}
