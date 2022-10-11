package com.example.team_pro_ex.Service.mypetboard.Beauty;

import com.example.team_pro_ex.Entity.mypetboard.beauty.Beauty;
import com.example.team_pro_ex.Entity.mypetboard.common.BeautyImage;

import com.example.team_pro_ex.repository.image.BeautyImageRepository;
import com.example.team_pro_ex.repository.mypetboard.beauty.BeautyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BeautyServiceImpl implements BeautyService{

    private final BeautyRepository beautyRepo;
    private final BeautyImageRepository beautyImageRepo;

    @Autowired
    protected BeautyServiceImpl(BeautyRepository beautyRepo, BeautyImageRepository beautyImageRepo) {
        this.beautyRepo = beautyRepo;
        this.beautyImageRepo = beautyImageRepo;
    }


    @Override
    public Page<Beauty> getBeautyList(Pageable pageable) {

        return beautyRepo.findAll(pageable);
    }

    @Override
    public Long insertBeauty(Beauty beauty) {
        return beautyRepo.save(beauty).getSeq();
    }

    @Override
    public Beauty getBeauty(Beauty beauty) {
        return beautyRepo.findById(beauty.getSeq()).get();
    }

    @Override
    public void updateBeauty(Beauty beauty) {

    }

    @Override
    public void deleteBeauty(Beauty beauty) {

    }

    @Override
    public Long insertBeautyImage(BeautyImage beautyImage) {

        return beautyImageRepo.save(beautyImage).getSeq();
    }

    @Override
    public BeautyImage getbeautyImageEntity(Long BeautySeq) {

        return beautyImageRepo.findByBeautySeq(BeautySeq);
    }
}
