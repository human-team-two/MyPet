package com.example.team_pro_ex.com.Service.businessMember;

import com.example.team_pro_ex.com.Entity.business_Member.businessMember;
import com.example.team_pro_ex.com.persistence.businessMember.businessMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class businessMemberServiceImpl implements businessMemberService{

    private final businessMemberRepository businessMemberRepo;


    @Autowired
    protected businessMemberServiceImpl(businessMemberRepository businessMemberRepo){
        this.businessMemberRepo = businessMemberRepo;
    }

    //회원 전체조회
    @Override
    public List<businessMember> get_BusinessMemberList() {
        System.out.println("--------회원 목록-------");
        return (List<businessMember>) businessMemberRepo.findAll();
    }

    @Override
    public List<businessMember> getBusinessListEncodingByBusinessMemberList(List<businessMember> businessMember) {
        return businessMember;
    }

    //회원 가입
    @Override
    public void insertBusinessMember(businessMember businessmember) {
        System.out.println("---------회원가입---------");
        businessMember findBusinessMember = businessMemberRepo.findMemberById(businessmember.getId());

        if(findBusinessMember != null){
            System.out.println("중복된 아이디 입니다.");
        }else{
            businessMemberRepo.save(businessmember);
        }



    }
    //myPage => 회원정보를 가져온다.
    @Override
    public businessMember getBusinessMemeber(businessMember businessmember) {
        //특정회원을 검색하여 리턴하고, 만약 검색 결과가 없으면 null을 리턴한다.
        Optional<businessMember> findBusiness = businessMemberRepo.findById
                                                (businessmember.getBusiness_Number_Seq());
        if(findBusiness.isPresent())
            return businessMemberRepo.findById(businessmember.getBusiness_Number_Seq()).get();
            else return null;
    }
    //회원정보 수정
    @Override
    public void updateBusinessMember(businessMember businessmember) {
        businessMember findBusiness = businessMemberRepo.findById(businessmember.getBusiness_Number_Seq()).get();

        findBusiness.setPassword(businessmember.getPassword());
        findBusiness.setBusinessNumber(businessmember.getBusinessNumber());
        findBusiness.setPhoneNumber(businessmember.getPhoneNumber());

        System.out.println("------------회원정보 수정------------");
        System.out.println(findBusiness.getPassword());
        System.out.println(findBusiness.getBusinessNumber());
        System.out.println(findBusiness.getPhoneNumber());
        System.out.println("------------회원정보 수정------------");

        businessMemberRepo.save(findBusiness);
    }
    // 우리는 회원정보를 삭제하지 않기 때문에 비즈니스 레포지토리에서 jpql?을 이용해서
    // 아이디, 회원탈퇴날짜, 가입상태를 제외한 모든 데이터를 null처리를 한다.
    @Override
    public void deleteUpdateBusinessMember(businessMember businessmember) {
        System.out.println("----------회원 탈퇴----------");
        businessMemberRepo.updateDelete(businessmember.getBusiness_Number_Seq());
    }

    //아이디 유효성 검사
    @Override
    public Map<String, String> businessMember_Availabilty(Errors errors) {
        //아이디 유효성 처리는 MemberServiceImpl 설명을 참고
        Map<String, String> availability_ID =  new HashMap<>();

        for(FieldError error : errors.getFieldErrors()){
            String businessMember_availability_ID = String.format("businessMember_%s", error.getField());
            availability_ID.put(businessMember_availability_ID, error.getDefaultMessage());
        }
        return availability_ID;
    }
    //아이디 찾기
    @Override
    public boolean booleanSearchUserById(businessMember businessmember) {
        for (businessMember businessMembers : businessMemberRepo.findByIdContains(businessmember.getId())){
            System.out.println("아이디 : " + businessMembers.getId());
        }
        return false;
    }
    //아이디 찾기
    @Override
    public businessMember getBusinessMemberWhereId(String id) {
        return businessMemberRepo.findMemberById(id);
    }
}
