package com.example.team_pro_ex.Service.mypetboard.hotel;

import com.example.team_pro_ex.Entity.mypetboard.hotel.Hotel;
import com.example.team_pro_ex.Entity.mypetboard.hotel.HotelRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HotelRoomServiceImpl implements HotelRoomService {
    @Override
    public Page<Hotel> getHotelRoomList(Pageable pageable) {
        return null;
    }

    @Override
    public HotelRoom insertRoom(HotelRoom hotelroom) {
        return null;
    }

    @Override
    public HotelRoom getHotel(HotelRoom hotelRoom) {
        return null;
    }

    @Override
    public void updateHotel(HotelRoom hotelRoom) {

    }

    @Override
    public void deleteHotel(HotelRoom hotelRoom) {

    }
}
