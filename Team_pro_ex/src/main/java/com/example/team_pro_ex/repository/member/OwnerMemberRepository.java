package com.example.team_pro_ex.repository.member;

import com.example.team_pro_ex.Entity.member.OwnerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerMemberRepository extends JpaRepository<OwnerMember,Long> {
}
