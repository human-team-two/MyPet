package com.example.team_pro_ex.repository.review;

import com.example.team_pro_ex.Entity.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "select r from Review r where r.writer like %?1% or r.title like %?1% or r.content like %?1%")
    List<Review> findByTitleLikeAndWriterLikeAndContentLike(String keyword);

    //0929
    Page<Review> findByTitleContaining(Pageable pageable, String keyword);

    Page<Review> findByContentContaining(Pageable pageable, String keyword);

    Page<Review> findByWriterContaining(Pageable pageable, String keyword);
}