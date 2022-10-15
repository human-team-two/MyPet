package com.example.team_pro_ex.Service.review;

import com.example.team_pro_ex.Entity.review.Review;
import com.example.team_pro_ex.repository.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepo;

    @Autowired
    protected ReviewServiceImpl(ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public List<Review> getReviewList(Review review){
        System.out.println("-----service getReviewList-----");
        return (List<Review>) reviewRepo.findAll();
    }
    @Override
    public void insertReview(Review review){
        System.out.println("-----service insertReview-----");
        review.setCreateDate(new Date());
        reviewRepo.save(review);
    }
    @Override
    public Review getReview(Review review){
        System.out.println("-----service getReview-----");
        return reviewRepo.findById(review.getSeq()).get();
    }
    @Override
    public Review findById(Long seq){
        return reviewRepo.findById(seq).orElse(null);
    }
    @Override
    public void updateReview(Review review){
        System.out.println("-----service updateReview-----");
        Review findReview = reviewRepo.findById(review.getSeq()).get();
        findReview.setTitle(review.getTitle());
        findReview.setContent(review.getContent());
        reviewRepo.save(findReview);
    }
    @Override
    public void removeReview(Review review){
        System.out.println("-----service deleteReview------");
        reviewRepo.deleteById(review.getSeq());
    }

    @Override
    public List<Review> searchReviewAll(String keyword){
        System.out.println("-----service findReview-----");
        return reviewRepo.findByTitleLikeAndWriterLikeAndContentLike(keyword);
    }
    @Override
    public Page<Review> findAll(Pageable pageable, String keyword){
        return reviewRepo.findByTitleLikeAndWriterLikeAndContentLike(pageable, keyword);
    }
    @Override
    public Page<Review> findByTitle(Pageable pageable, String keyword) {
        System.out.println("-----service findByTitle-----");
        return reviewRepo.findByTitleContaining(pageable, keyword);
    }
    @Override
    public Page<Review> findByContent(Pageable pageable, String keyword) {
        System.out.println("-----service findByContent-----");
        return reviewRepo.findByContentContaining(pageable, keyword);
    }
    @Override
    public Page<Review> findByWriter(Pageable pageable, String keyword) {
        System.out.println("-----service findByWriter-----");
        return reviewRepo.findByWriterContaining(pageable, keyword);
    }
    //회원 아이디(자신의 아이디)로만 문의사항 삭제하기 위해서
    @Override
    public List<Review> findALLMemberorReview() {
        return reviewRepo.findAllByMember();
    }


}
