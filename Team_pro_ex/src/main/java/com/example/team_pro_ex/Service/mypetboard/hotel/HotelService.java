package com.example.team_pro_ex.Service.mypetboard.hotel;
import com.example.team_pro_ex.Entity.mypetboard.common.HotelImage;
import com.example.team_pro_ex.Entity.mypetboard.hotel.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {

    Page<Hotel> getHotelList(Pageable pageable);

    Long insertHotel(Hotel hotel);

    Hotel getHotel(Hotel hotel);

    void updateHotel(Hotel hotel);

    void deleteHotel(Hotel hotel);

    Long insertHotelImage(HotelImage hotelImage);

    HotelImage getHotelImageEntity(Long hotelSeq);

}
