package com.example.team_pro_ex.repository.mypetboard.hotel;

import com.example.team_pro_ex.Entity.mypetboard.hotel.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {

}
