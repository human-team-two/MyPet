package com.example.team_pro_ex.Entity.review;

import com.example.team_pro_ex.Entity.member.Member;
import com.example.team_pro_ex.Entity.time.BaseTime;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
public class Review extends BaseTime {
    //문의사항~!
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_Seq")
    private Long seq;
    //제목
    @Column(name = "review_Title",length = 40, nullable = false)
    private String title;
    //작성자
    @Column(name="review_Writer")
    private String writer;
    //내용
    @Column(name= "review_Content",length = 500, nullable = false)
    private String content;
    //분류
    @Column(name="review_Category")
    private String category;
    //검색할 떄 키워드
    @Column(name="review_Keyword")
    private String keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_Number_Seq")
    private Member member;

//    private String scategory;




}
