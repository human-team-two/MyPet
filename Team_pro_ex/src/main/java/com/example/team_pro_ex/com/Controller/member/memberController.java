package com.example.team_pro_ex.com.Controller.member;

import com.example.team_pro_ex.com.Entity.member.Member;
import com.example.team_pro_ex.com.Service.member.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
// 세션에 상태 정보를 저장할 때 사용하는데, @SessionAttributes 뒤에 ("member") 라고 설정했기 때문에
// "member" 라는 이름으로 저장된 데이터를 자동으로 세션으로 등록
@SessionAttributes("member")
@RequestMapping(path = "/Member")
public class memberController {


    private final memberService memberService;

    @Autowired
    protected memberController(memberService memberservice){
        this.memberService = memberservice;
    }


    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/memberList/members")
    public String membrerList(Model model){
        model.addAttribute("member", memberService.getMemberListEncodingByMemberList(
                memberService.getMemberList()));
        return  "/Member/memberList/members";
    }

    @GetMapping("/mJoin/Join")
    public String insertMember(Member member, Model model){
        System.out.println("get mapping account !!");
        System.out.println("get방식으로 인한 Join페이지 = 우리가 처음 join페이지를 들어갈 떄는 null값이 뜰 수 밖에없다.");
        System.out.println("왜냐!!?!? 값이 없으니까!");
        System.out.println(member.getName());
        System.out.println(member.getPetW());
        Member member_1 = new Member(
                member.getMember_Number_Seq(), // 회원인원 몇명인지!
                member.getId(), //아이디, 회원등록 번호 : PK
                member.getPassword(), //비밀번호
                member.getName(), //이름
                member.getYear(), //(회원) 생년월일
                member.getPhoneNumber(), // 핸드폰 번호
                member.getAddress(), //주소
                member.getPetT(), // 애견, 애묘 : 종류
                member.getPetD(), // 애견, 애묘 : 생년월일
                member.getPetW(), // 애견, 애묘 : 몸무게
                member.getRole(), // 권한 : 관리자, 사업자, 회원
                member.getJoinM());// Y는 현재 가입상태 => 모든 회원은 처음 가입할 떄 가입상태 Y로 시작을 한다. /
        // 탈퇴할 경우는 update로 N으로 수정된다.
        model.addAttribute("member", member_1);
        return "Member/mJoin/Join";
    }
    @PostMapping("/mJoin/Join")
    public String insertMember(@Valid Member member, Errors errors, Model model){
        System.out.println("---check---");
        System.out.println("---PostMapping 실제로 여기서 값이 들어감---");
        System.out.println("아이디 : "+ member.getId());
        System.out.println("비밀번호 : "+ member.getPassword());
        System.out.println("생년월일 : "+ member.getYear());
        System.out.println("이름 : "+ member.getName());
        System.out.println("폰번 : "+ member.getPhoneNumber());
        System.out.println("주소 : "+ member.getAddress());
        System.out.println("펫 종류 :"+ member.getPetT());
        System.out.println("펫 생년 : "+ member.getPetD());
        System.out.println("펫 몸무게 :" +member.getPetW());
        System.out.println("권한 : " + member.getRole());
        System.out.println("가입상태 : " +member.getJoinM());
        //@Valid : 클라이언트 입력 데이터가 dto클래스로 캡슐화되어 넘어올 때, 유효성을 체크하라는 어노테이션
        //Member에서 작성한 어노테이션을 기준으로 유효성 체크
        //여기서 Errors객체는 Member의 필드 유효성 검사 오류에 대한 정보를 저장하고 노출한다.
        //errors.hasErrors() : 유효성 검사에 실패한 필드가 있는지 확인
        if(errors.hasErrors()){
            //회원가입 실패 시, 입력 데이터를 유지
            model.addAttribute("member", member);
            System.out.println(member.getId());
            //회원가입 실패 시, 회원가입 페이지에서 입력했던 정보들을 그대로 유지하기 위해 입력받았던 데이터를 그대로 할당합니다.
            //insertMember(Member member) 함수에 파라미터를 정의해준 이유입니다.
            //Validation 관점에서는 필요없는 부분이지만, UX 측면에서 구현해주는 것이 좋다.
            //물론, thymeleaf에서도 코드가 들어가야 한다.

            //유효성 통과 못한 필드와 메세지를 핸들링
            Map<String, String> member_Availability = memberService.member_Availability(errors);
            for(String key : member_Availability.keySet()){
                model.addAttribute(key, member_Availability.get(key));
            }
            return "/Member/mJoin/Join";
        }
        Member findMember = memberService.getMemberWhereId(member.getId());
        System.out.println("중복 : " + member.getId());
        if(findMember != null){
            System.out.println("중복 된 아이디 입니다." + member.getId());
            return "/Member/mJoin/Join";
        }else{
            System.out.println("회원가입 되었습니다! : " + member.getId());
            memberService.insertMember(member);
            // 회원 가입에 성공하면 메인페이지로 다시 돌아오게 했다.
            // 로그인페이지로 다시 돌아가면 인증된 아이디이기 때문에
            // 아이디가 중복이 됐다고 출력문이 나오기 때문이다.
            //redirect:/Member/Login
            return "/index";
        }
    }

    @GetMapping("/mUpdate/Update") //마이 페이지 수정폼
    public String updateM(Member member, Model model){
        System.out.println("get mapping account !!");
        System.out.println("get방식으로 인한 Join페이지 = 우리가 처음 join페이지를 들어갈 떄는 null값이 뜰 수 밖에없다.");
        System.out.println("왜냐!!?!? 값이 없으니까!");
        System.out.println(member.getName());
        System.out.println(member.getPetW());
        Member member_1 = new Member( // null값이라서 값을 받아올 수가 없다....
                member.getMember_Number_Seq(), // 회원인원 몇명인지!
                member.getId(), //아이디, 회원등록 번호 : PK
                member.getPassword(), //비밀번호
                member.getName(), //이름
                member.getYear(), //(회원) 생년월일
                member.getPhoneNumber(), // 핸드폰 번호
                member.getAddress(), //주소
                member.getPetT(), // 애견, 애묘 : 종류
                member.getPetD(), // 애견, 애묘 : 생년월일
                member.getPetW(), // 애견, 애묘 : 몸무게
                member.getRole(), // 권한 : 관리자, 사업자, 회원
                member.getJoinM());// Y는 현재 가입상태 => 모든 회원은 처음 가입할 떄 가입상태 Y로 시작을 한다. /
        // 탈퇴할 경우는 update로 N으로 수정된다.
        model.addAttribute("member", member_1);;
        return "/Member/mUpdate/Update";
    }


    @PostMapping("/mUpdate/Update") // 실제로 수정 되는 메소드
    public String updateM(@Valid Member member, Errors errors, Model model){
        System.out.println("---회원 정보 수정이 이루어 진다.---");
        System.out.println("---check---");
        System.out.println("---PostMapping 실제로 여기서 값이 들어감---");
        System.out.println("아이디 : "+ member.getId());
        System.out.println("비밀번호 : "+ member.getPassword());
        System.out.println("이름 : "+ member.getName());
        System.out.println("폰번 : "+ member.getPhoneNumber());
        System.out.println("주소 : "+ member.getAddress());
        System.out.println("펫 종류 :"+ member.getPetT());
        System.out.println("펫 생년 : "+ member.getPetD());
        System.out.println("펫 몸무게 :" +member.getPetW());
        System.out.println("권한 : " + member.getRole());
        System.out.println("가입상태 : " +member.getJoinM());
        //@Valid : 클라이언트 입력 데이터가 dto클래스로 캡슐화되어 넘어올 때, 유효성을 체크하라는 어노테이션
        //Member에서 작성한 어노테이션을 기준으로 유효성 체크
        //여기서 Errors객체는 Member의 필드 유효성 검사 오류에 대한 정보를 저장하고 노출한다.
        //errors.hasErrors() : 유효성 검사에 실패한 필드가 있는지 확인
        if(errors.hasErrors()){
            //회원가입 실패 시, 입력 데이터를 유지
            model.addAttribute("member", member);
            System.out.println(member.getId());
            //회원가입 실패 시, 회원가입 페이지에서 입력했던 정보들을 그대로 유지하기 위해 입력받았던 데이터를 그대로 할당합니다.
            //insertMember(Member member) 함수에 파라미터를 정의해준 이유입니다.
            //Validation 관점에서는 필요없는 부분이지만, UX 측면에서 구현해주는 것이 좋다.
            //물론, thymeleaf에서도 코드가 들어가야 한다.

            //유효성 통과 못한 필드와 메세지를 핸들링
            Map<String, String> member_Availability = memberService.member_Availability(errors);
            for(String key : member_Availability.keySet()){
                model.addAttribute(key, member_Availability.get(key));
            }
            return "redirect:/Member/mUpdate/Update"; //로그안 페이지로 왜 안돌아가는지 모르겠다.
        }
        memberService.updateMember(member);

        return "redirect:/Member/loginPage";
    }

    //로그인
    @GetMapping("/Login")
    public String loginView(){

        return "/Member/Login";
    }
    //로그인
    @PostMapping("/Login")
    public String login(Member member, Model model){
        Member findMember = memberService.getMember(member);
        // 아이디, 비번 알치해야지 로그인 가능
        // 회원 로그인을 할 경우 아이디와 비밀번호가 일치하지 않으면 로그인 페이지로 되돌아 온다.
        // 로그인 성공시 회원 페이지로 이동
        if(findMember != null
                && findMember.getPassword().equals(member.getPassword())
                && findMember.getId().equals(member.getId())){
            model.addAttribute("member", findMember);
            System.out.println("로그인 됐습니다!");
            return "redirect:/Member/loginPage";
        }else {
            model.addAttribute("loginErroMsg", "아이디 비밀번호를 확인해주세요");
            System.out.println("아이디, 비밀번호 다시 입력해주세요!");
            return "redirect:/Member/Login";
        }
    }
    //로그아웃
    @GetMapping("/logout")
    public String logout(SessionStatus status){
        status.setComplete();
        System.out.println("로그아웃");
        return "redirect:index";
    }

    @GetMapping("/loginPage")
    public void loginPage(){
    }


    //회원을 삭제하는게 아니라 수정한다. ID, Name, Join_m 및 날짜 테이블의 join_O을 제외한 값 전부 Null
    @PostMapping("/mDelete/upDelete")
    public String deleteUpdateMember(Member member){
        System.out.println("———delete———");
        memberService.deleteUpdateMember(member);
        return "redirect:Member/loginPage";
    }

    //아이디 찾기 = 핸드폰 번호로 찾기(from 화면만 보임)
    @GetMapping("/selectMember/select")
    public String selectMember() {
        return "/Member/selectMember/select";
    }

    //핸드폰으로 아이디찾기 => 결과값을 보여준다.
    @PostMapping("/selectMember/select")
    public String resultMember(Member member, Model model) {
        System.out.println("———select ID————");
        System.out.println(memberService.booleanSearchUserById(member));
        model.addAttribute("member", memberService.getMemberWhereId(member.getId()));
        return "/Member/selectMember/result";
    }

}
