package com.example.team_pro_ex.Controller.member;

import com.example.team_pro_ex.Entity.member.OwnerMember;
import com.example.team_pro_ex.Entity.mypetboard.accommodation.Accommodation;
import com.example.team_pro_ex.Service.member.OwnerMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping(path = "/member/ownermember")
public class OwnerMemberControlller {

    private final OwnerMemberService ownerMemberService;

    @Autowired
    public OwnerMemberControlller(OwnerMemberService ownerMemberService) {
        this.ownerMemberService = ownerMemberService;
    }
    @GetMapping("/insertOwnerMember")
    public String insertOwnerMemberView(Accommodation hotel, Model model , OwnerMember ownerMember) {

        return "member/ownermember/insertOwnerMember";
    }
    @PostMapping("/insertOwnerMember")
    public String insertOwnerMember(OwnerMember ownerMember) {
        ownerMember.setCreateDate(new Date());
        ownerMemberService.insertOwnerMember(ownerMember);
        return "index2";
    }



}
