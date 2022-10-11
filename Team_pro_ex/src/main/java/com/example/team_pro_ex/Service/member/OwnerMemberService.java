package com.example.team_pro_ex.Service.member;

import com.example.team_pro_ex.Entity.member.OwnerMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OwnerMemberService {
    Page<OwnerMember> getOwnerMemberList(Pageable pageable);

    void insertOwnerMember(OwnerMember ownerMember);

    OwnerMember getOwnerMember(OwnerMember ownerMember);

    void updateOwnerMember(OwnerMember ownerMember);

    void deleteOwnerMember(OwnerMember ownerMember);
}
