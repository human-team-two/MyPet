package com.example.teampro.Repository;

import com.example.teampro.Entity.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findById(String id);





}
