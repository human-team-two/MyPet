package com.example.team_pro_ex.Service.mypetboard.accommodation;

import com.example.team_pro_ex.Entity.mypetboard.accommodation.Accommodation;
import com.example.team_pro_ex.Entity.mypetboard.common.AccommodationImage;
import com.example.team_pro_ex.exception.DataNotFoundException;
import com.example.team_pro_ex.repository.image.AccommodationImageRepository;

import com.example.team_pro_ex.repository.mypetboard.Accommodation.RoomRepository;
import com.example.team_pro_ex.repository.mypetboard.Accommodation.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepo;
    private final AccommodationImageRepository accommodationImageRepo;

    private final RoomRepository accommodationRoomRepo;

    @Autowired
    protected AccommodationServiceImpl(AccommodationRepository accommodationRepo, AccommodationImageRepository accommodationImageRepo, RoomRepository accommodationRoomRepo) {
        this.accommodationRepo = accommodationRepo;
        this.accommodationImageRepo = accommodationImageRepo;
        this.accommodationRoomRepo = accommodationRoomRepo;
    }

    @Override
    public Page<Accommodation> getAccommodationList(Pageable pageable) {

        return accommodationRepo.findAll(pageable);
    }

    @Override
    public Long insertAccommodation(Accommodation accommodation) {

       return accommodationRepo.save(accommodation).getSeq();
    }



    @Override
    public Accommodation getAccommodation(Accommodation accommodation) {

        return accommodationRepo.findById(accommodation.getSeq()).get();
    }

    @Override
    public Accommodation getAccommodationAnswer(Long seq) {
        Optional<Accommodation> accommodation = accommodationRepo.findById(seq);
        if(accommodation.isPresent()) {
            return accommodation.get();
        }
        else {
            throw new DataNotFoundException("Accommodation not found");
        }

    }

    @Override
    public void updateAccommodation(Accommodation accommodation) {
        Accommodation updateAccommodation = accommodationRepo.findById(accommodation.getSeq()).get();

        updateAccommodation.setDetailCategory(accommodation.getDetailCategory());
        updateAccommodation.setStoreName(accommodation.getStoreName());
        updateAccommodation.setInfo(accommodation.getInfo());
        updateAccommodation.setAddress(accommodation.getAddress());
        updateAccommodation.setDetailAddress(accommodation.getDetailAddress());
        updateAccommodation.setPostcode(accommodation.getPostcode());
        updateAccommodation.setPetSize(accommodation.getPetSize());

        accommodationRepo.save(updateAccommodation);
    }

    @Override
    public void deleteAccommodation(Accommodation accommodation) {
        accommodationRepo.deleteById(accommodation.getSeq());
    }

    @Override
    public Long insertAccommodationImage(AccommodationImage accommodationImage) {

        return accommodationImageRepo.save(accommodationImage).getSeq();
    }

    @Override
    public AccommodationImage getAccommodationImageEntity(Long accommodationSeq) {

        return accommodationImageRepo.findByAccommodationSeq(accommodationSeq);
    }



    @Override
    public Page<Accommodation> getAccommodationContainsName(String keyword, Pageable pageable) {
        return accommodationRepo.findByStoreNameContaining(keyword, pageable);
    }

    @Override
    public Page<Accommodation> findCategoryAndKeyword(String searchKeyword, Pageable pageable) {
        return accommodationRepo.findCategoryAndKeyword(searchKeyword, pageable);
    }

    @Override
    public Accommodation getAccommodationRequest(Long seq) {

        Optional<Accommodation> question = accommodationRepo.findById(seq);

        if(question.isPresent()) {
            return question.get();
        }
        else {
            throw new DataNotFoundException("not found");
            }
        }

    @Override
    public List<AccommodationImage> getAccommodationImageList(AccommodationImage accommodationImage) {

        return accommodationImageRepo.findAll();
    }


}


//    @Override
//    public Page<Accommodation> getAccommodationCategoryLikeAndStoreNameContaining(String detailCategory, String keyword, Pageable pageable) {
//        return accommodationRepo.findByDetailCategoryLikeAndStoreNameContaining(detailCategory,keyword,pageable);
//    }



