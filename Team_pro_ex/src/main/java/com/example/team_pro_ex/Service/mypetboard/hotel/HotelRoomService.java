package com.example.team_pro_ex.Service.mypetboard.hotel;


import com.example.team_pro_ex.Entity.mypetboard.hotel.Hotel;
import com.example.team_pro_ex.Entity.mypetboard.hotel.HotelRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelRoomService {

    Page<Hotel> getHotelRoomList(Pageable pageable);

    HotelRoom insertRoom(HotelRoom hotelroom);

    HotelRoom getHotel(HotelRoom hotelRoom);

    void updateHotel(HotelRoom hotelRoom);

    void deleteHotel(HotelRoom hotelRoom);
}
