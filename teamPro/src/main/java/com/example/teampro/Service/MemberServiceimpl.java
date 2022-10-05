package com.example.teampro.Service;

import com.example.teampro.Entity.Member.Member;
import com.example.teampro.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberServiceimpl implements MemberService, UserDetailsService {


    private final MemberRepository memberRepository;


    @Autowired
    protected MemberServiceimpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //회원가입
    @Override
    public Member insertMember(Member member) {
        idCheck(member);
        return memberRepository.save(member);
    }
    //회원 아이디 중복검사
    @Override
    public void idCheck(Member member) {
        Member findMember = memberRepository.findById(member.getId());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public void UpdateMember(Member member) {

    }

    @Override
    public void deleteUpdateMember(Member member) {

    }

    @Override //유효성 검사
    public Map<String, String> member_Availability(Errors errors) {
        //유효성 검사에 실패한 필드들은 Map 자료구조를 통해 키값과 에러 메시지를 응답한다.
        //Key : valid_{dto 필드명}
        //Message : dto에서 작성한 message 값
        //유효성 검사에 실패한 필드 목록을 받아 미리 정의된 메시지를 가져와 Map에 넣어준다.
        Map<String, String> availability_ID = new HashMap<>();
        /* 유효성 검사에 실패한 필드 목록을 받음 */
        // errors.getFieldErrors() : 유효성 검사에 실패한 필드 목록을 가져옴
        for(FieldError error : errors.getFieldErrors()){
            //유효성 검사에 실패한 필드명을 가져옵니다. : error.getField() / 키 : "members_%s"  = > mevers_dto필드명
            String member_availability_ID = String.format("valid_%s", error.getField());
            //error.getDefaultMessage() : 유효성 검사에 실패한 필드에 정의된 메시지를 가져옵니다.
            availability_ID.put(member_availability_ID, error.getDefaultMessage());
        }
        return availability_ID;
    }

    @Override
    public boolean booleanSearchUserById(Member member) {
        return false;
    }

    @Override
    public Member getMemberWhereId(String id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Member member = memberRepository.findById(id);

        if(member == null){
            throw new UsernameNotFoundException(id);
        }
        return User.builder()
                .username(member.getId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
