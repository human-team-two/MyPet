package com.example.team_pro_ex.Entity.review;

import com.example.team_pro_ex.Entity.time.BaseTime;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
public class Review extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 40, nullable = false)
    private String title;

    private String writer;

    @Column(length = 500, nullable = false)
    private String content;

    private String category;

    private String keyword;

//    private String scategory;




}
