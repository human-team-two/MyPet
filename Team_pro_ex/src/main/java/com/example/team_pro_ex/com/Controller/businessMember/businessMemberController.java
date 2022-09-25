package com.example.team_pro_ex.com.Controller.businessMember;

import com.example.team_pro_ex.com.Entity.business_Member.businessMember;
import com.example.team_pro_ex.com.Service.businessMember.businessMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("businessMember")
@RequestMapping(path = "/businessMember")
public class businessMemberController {

    private final businessMemberService businessMemberServiec;

    @Autowired
    protected businessMemberController(businessMemberService businessMemberServiec){
        this.businessMemberServiec = businessMemberServiec;
    }

    @GetMapping("/bmList/bm_List")
    public String businessMember(Model model){
        model.addAttribute("businessMember",
                businessMemberServiec.getBusinessListEncodingByBusinessMemberList
                        (businessMemberServiec.get_BusinessMemberList()));
        return "/businessMember/bmList/bm_List";
    }
    //회원 가입 페이지 = 실제로 여기서 가입이 이루어지지는 않는다.
    @GetMapping("/bmJoin/bm_Join")
    public String insertBusinessMember(businessMember businessMember, Model model){
        businessMember insert_businessMember = new businessMember(
                businessMember.getBusiness_Number_Seq(), //회원번호
                businessMember.getId(), //회원 아이디
                businessMember.getPassword(), //회원 비밀번호
                businessMember.getBusinessNumber(), //사업자 번호
                businessMember.getPhoneNumber(), //회원 핸드폰 번호
                businessMember.getJoinM(), //가입상태
                businessMember.getRole()); //권한
                model.addAttribute("businessMember", insert_businessMember);
                return "/businessMember/bmJoin/bm_Join";
    }
    //회원 가입이 실질적으로 이루어진다.
    @PostMapping("/bmJoin/bm_Join")
    public String insertBusinessMember(@Valid businessMember businessMember, Errors errors, Model model){
        System.out.println("-------실질적으로 회원가입이 이루어진다.-------");
        System.out.println("사업자 seq번호 : " + businessMember.getBusiness_Number_Seq());
        System.out.println("아이디 : " + businessMember.getId());
        System.out.println("비밀번호 : " + businessMember.getPassword());
        System.out.println("사업자 번호 : " + businessMember.getBusinessNumber());
        System.out.println("사업자 핸드폰 번호 : " + businessMember.getPhoneNumber());
        System.out.println("가입상태 : " + businessMember.getJoinM());
        System.out.println("권한 : " + businessMember.getRole());

        if(errors.hasErrors()){
            model.addAttribute("businessMember", businessMember);
            System.out.println(businessMember.getId());
            Map<String, String> businessMember_Availability =
                    businessMemberServiec.businessMember_Availabilty(errors);
            for(String key : businessMember_Availability.keySet()){
                model.addAttribute(key, businessMember_Availability.get(key));
            }
            return "/businessMember/bmJoin/bm_Join";
        }
        businessMemberServiec.insertBusinessMember(businessMember);
        return "redirect:/businessMember/LoginBm";

    }
    //회원 수정 => 실질적으로 회원의 수정이 이루어지지는 않는다. 회원의 기본 정보가 나온다. 거기서 아이디를 제외한 것을 수정한다.
    @GetMapping("/bmUpdate/bm_Update")
    public String myPage(businessMember businessMember, Model model){
        System.out.println("--------회원의 정보가 나온다.--------");
        businessMember update_BusinessMember = new businessMember(
                businessMember.getBusiness_Number_Seq(), //회원번호
                businessMember.getId(), //회원 아이디
                businessMember.getPassword(), //회원 비밀번호
                businessMember.getBusinessNumber(), //사업자 번호
                businessMember.getPhoneNumber(), //회원 핸드폰 번호
                businessMember.getJoinM(), //가입상태
                businessMember.getRole()); //권한
        model.addAttribute("businessMember", update_BusinessMember);
        return "/businessMember/bmUpdate/bm_Update";

    }
    //회원 수정이 실질적으로 이루어지는 곳
    @PostMapping("/bmUpdate/bm_Update")
    public String myPage(@Valid businessMember businessMember, Model model, Errors errors){
        System.out.println("-------실질적으로 회원수정이 이루어진다.-------");
        System.out.println("사업자 번호 : " + businessMember.getBusiness_Number_Seq());
        System.out.println("아이디 : " + businessMember.getId());
        System.out.println("비밀번호 : " + businessMember.getPassword());
        System.out.println("사업자 번호 : " + businessMember.getBusinessNumber());
        System.out.println("사업자 핸드폰 번호 : " + businessMember.getPhoneNumber());
        System.out.println("가입상태 : " + businessMember.getJoinM());
        System.out.println("권한 : " + businessMember.getRole());

        if(errors.hasErrors()){
            model.addAttribute("businessMember", businessMember);
            System.out.println("아이디 유효성 검사부분 : " +businessMember.getId());

            Map<String, String> businessMember_Availability =
                    businessMemberServiec.businessMember_Availabilty(errors);
            for (String key : businessMember_Availability.keySet()){
                model.addAttribute(key, businessMember_Availability.get(key));
            }
            return "redirect:/businessMember/bmUpdate/bm_Update";
        }
        businessMemberServiec.updateBusinessMember(businessMember);
        return "redirect:/businessMember/bmLoginPage";

    }

    //로그인 페이지
    @GetMapping("/LoginBm")
    public void loginView(){
    }
    @GetMapping("/bmLoginPage")
    public void loginPageBMGo(){

    }

    //로그인 페이지
    @PostMapping("/LoginBm")
    public String login(businessMember businessMember, Model model){
        businessMember findeBusinessMember = businessMemberServiec.getBusinessMemeber(businessMember);

        if(findeBusinessMember != null
            && findeBusinessMember.getPassword().equals(businessMember.getPassword())
                && findeBusinessMember.getId().equals(businessMember.getId())){
            model.addAttribute("businessMember", findeBusinessMember);
            System.out.println("로그인 성공했습니다.");
            return "redirect:/businessMember/bmLoginPage";
        }else{
            System.out.println("아이디, 비밀번호를 다시 확인해주세요!");
            return "redirect:/businessMember/LoginBm";
        }

    }
    //로그아웃
    @GetMapping("/logout")
    public String logout(SessionStatus status){
        status.setComplete();
        System.out.println("로그아웃");
        return "redirect:/businessMember/LoginBm";
    }
    //회원탈퇴라고는 하지만 실질적으로 회원은 아이디, 가입상태, 탈퇴날짜를 제외한 데이터를 null처리를 한 것이다.
    @PostMapping("/bmUpdate/upDelete")
    public String deleteUpdateBusinessMember(businessMember businessMember){
        System.out.println("---------회원탈퇴-------");
        businessMemberServiec.deleteUpdateBusinessMember(businessMember);
        return "redirect:/businessMember/businessMemberPage";
    }
    //아이디 찾기 = 핸드폰 번호로 찾기(form 화면만 보임)
    @GetMapping("/selectBusinessMember/selectBM")
    public String selectBusinessMember(){
        return "/businessMember/selectBusinessMember/selectBM";
    }

    //핸드폰으로 아이디 찾기 -> 결과값을 보여준다.
    @PostMapping("/selectBusinessMember/selectBM")
    public String selectBusinessMember(businessMember businessMember, Model model){
        System.out.println("----------아이디 찾기---------");
        System.out.println(businessMemberServiec.booleanSearchUserById(businessMember));
        model.addAttribute("businessMember",
                businessMemberServiec.getBusinessMemberWhereId(businessMember.getId()));
        return "/businessMember/selectBusinessMember/resultBM";
    }






}
