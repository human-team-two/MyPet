package com.example.team_pro_ex.Service.mypetboard.hotel;

import com.example.team_pro_ex.Entity.mypetboard.common.HotelImage;
import com.example.team_pro_ex.Entity.mypetboard.hotel.Hotel;
import com.example.team_pro_ex.repository.hotelImage.HotelImageRepository;
import com.example.team_pro_ex.repository.mypetboard.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepo;
    private final HotelImageRepository hotelImageRepo;


    @Autowired
    protected HotelServiceImpl(HotelRepository hotelRepo, HotelImageRepository hotelImageRepo) {
        this.hotelRepo = hotelRepo;
        this.hotelImageRepo = hotelImageRepo;
    }

    @Override
    public Page<Hotel> getHotelList(Pageable pageable) {

        return hotelRepo.findAll(pageable);
    }

    @Override
    public Long insertHotel(Hotel hotel) {

       return hotelRepo.save(hotel).getHotelSeq();
    }

    @Override
    public Hotel getHotel(Hotel hotel) {

        return null;
    }

    @Override
    public void updateHotel(Hotel hotel) {

    }

    @Override
    public void deleteHotel(Hotel hotel) {

    }

    @Override
    public Long insertHotelImage(HotelImage hotelImage) {

        return hotelImageRepo.save(hotelImage).getImageSeq();
    }

    @Override
    public HotelImage getHotelImageEntity(Long hotelSeq) {

        return hotelImageRepo.findByHotelSeq(hotelSeq);
    }
}
