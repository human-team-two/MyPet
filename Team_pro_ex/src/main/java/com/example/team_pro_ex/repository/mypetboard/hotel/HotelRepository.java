package com.example.team_pro_ex.repository.mypetboard.hotel;


import com.example.team_pro_ex.Entity.mypetboard.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
