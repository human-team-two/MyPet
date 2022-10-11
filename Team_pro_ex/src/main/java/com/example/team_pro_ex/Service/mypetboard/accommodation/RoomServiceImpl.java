package com.example.team_pro_ex.Service.mypetboard.accommodation;

import com.example.team_pro_ex.Entity.mypetboard.accommodation.Room;
import com.example.team_pro_ex.Entity.mypetboard.accommodation.Accommodation;
import com.example.team_pro_ex.Entity.mypetboard.common.RoomImage;
import com.example.team_pro_ex.Entity.mypetboard.accommodation.Room;
import com.example.team_pro_ex.repository.image.RoomImageRepository;
import com.example.team_pro_ex.repository.mypetboard.Accommodation.AccommodationRepository;
import com.example.team_pro_ex.repository.mypetboard.Accommodation.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl  implements  RoomService{

    private final RoomRepository roomRepo;
    private final RoomImageRepository roomImageRepo;

    private final AccommodationRepository accommodationRepo;



    @Autowired
    public RoomServiceImpl(RoomRepository roomRepo, RoomImageRepository roomImageRepo, AccommodationRepository accommodationRepo) {
        this.roomRepo = roomRepo;
        this.roomImageRepo = roomImageRepo;
        this.accommodationRepo = accommodationRepo;
      }


    @Override
    public List<Room> getRoomList() {
        return roomRepo.findAll();
    }

    @Override
    public Long insertRoom(Room room) {



        return roomRepo.save(room).getSeq();



    }


    @Override
    public Room getRoom(Room room) {
        return roomRepo.findById(room.getSeq()).get();
    }

    @Override
    public void updateRoom(Room room) {


    }

    @Override
    public void deleteRoom(Room room) {

    }

    @Override
    public Long insertRoomImage(RoomImage roomImage) {
        return roomImageRepo.save(roomImage).getSeq();
    }

    @Override
    public List<RoomImage> getRoomImageEntity(Long accSeq) {
        return roomImageRepo.findByAccSeq(accSeq);
    }



}
