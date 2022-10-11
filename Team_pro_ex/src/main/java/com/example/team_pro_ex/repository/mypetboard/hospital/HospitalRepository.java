package com.example.team_pro_ex.repository.mypetboard.hospital;


import com.example.team_pro_ex.Entity.mypetboard.hospital.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}

