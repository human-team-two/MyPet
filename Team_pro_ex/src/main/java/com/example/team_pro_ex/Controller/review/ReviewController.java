package com.example.team_pro_ex.Controller.review;

import com.example.team_pro_ex.Entity.review.Review;
import com.example.team_pro_ex.Service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    protected ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/mypetboard/review/insertreview")
    public void insertReview() {
        System.out.println("-----GET insertreview");
    }

    @PostMapping("/mypetboard/review/insertreview")
    public String insertReview(Review review) {
        System.out.println("-----POST insertreview");
        review.setCreateDate(new Date());
        reviewService.insertReview(review);
        System.out.println("-----insertreview= " + review);
        return "redirect:/mypetboard/review/reviewList";
    }
    //0929 카테고리 선택 후 검색기능
    @GetMapping("/mypetboard/review/reviewList")
    public String getreviewList(
            @RequestParam(value = "scategory",required = false, defaultValue = "")String scategory,
            @RequestParam(value = "keyword", required = false, defaultValue = "")String keyword,
            Pageable pageable, Model model) {
        System.out.println("-----GET getreviewList");
        System.out.println("scategory= " + scategory);
        System.out.println("keyword= " + keyword);

        Page<Review> reviews = null;
            if(scategory.equals("title")){
                reviews = reviewService.findByTitle(pageable, keyword);
            }else if (scategory.equals("content")) {
                reviews = reviewService.findByContent(pageable, keyword);
            } else if (scategory.equals("writer")) {
                reviews = reviewService.findByWriter(pageable, keyword);
            }else{
                reviews = reviewService.findAll(pageable, keyword);
            }
        model.addAttribute("reviewList", reviews);
        model.addAttribute("scategory", scategory);
        model.addAttribute("keyword", keyword);
        return "/mypetboard/review/reviewList";
    }

    @GetMapping("/mypetboard/review/getreview")
    public String getreview(Model model, Review review) {
        System.out.println("-----GET getreview");
        model.addAttribute("reviewList", reviewService.getReview(review));
        return "/mypetboard/review/getreview";
    }
    @GetMapping("/mypetboard/review/removeReview")
    public String removereview(Review review) {
        System.out.println("-----GET removereview");
        reviewService.removeReview(review);
        return "redirect:/mypetboard/review/reviewList";
    }
    @PostMapping("/mypetboard/review/updateReview")
    public String updatereview(Review review) {
        System.out.println("-----POST updatereview");
        reviewService.updateReview(review);
        return "redirect:/mypetboard/review/reviewList";
    }
}
