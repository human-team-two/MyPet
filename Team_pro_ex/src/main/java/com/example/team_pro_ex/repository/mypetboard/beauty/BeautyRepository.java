package com.example.team_pro_ex.repository.mypetboard.beauty;

import com.example.team_pro_ex.Entity.mypetboard.beauty.Beauty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyRepository extends JpaRepository<Beauty, Long> {
}
