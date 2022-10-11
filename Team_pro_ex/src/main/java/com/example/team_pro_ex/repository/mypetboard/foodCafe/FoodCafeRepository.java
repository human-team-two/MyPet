package com.example.team_pro_ex.repository.mypetboard.foodCafe;



import com.example.team_pro_ex.Entity.mypetboard.foodandcafe.FoodCafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCafeRepository extends JpaRepository<FoodCafe, Long> {
}
