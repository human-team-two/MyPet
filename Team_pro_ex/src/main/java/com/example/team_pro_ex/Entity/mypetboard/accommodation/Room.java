package com.example.team_pro_ex.Entity.mypetboard.accommodation;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_seq")
    private Long seq;

    //외래키 설정
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Acc_seq")
    private Accommodation accommodation;


    @Column(nullable = false)
    private String roomName;

    private String roomInfo;

    @Column(nullable = false)
    private String roomType;

    @Column(nullable = false)
    private Integer roomPrice;




    }










