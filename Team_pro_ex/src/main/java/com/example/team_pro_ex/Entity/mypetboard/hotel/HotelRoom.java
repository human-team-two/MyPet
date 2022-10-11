package com.example.team_pro_ex.Entity.mypetboard.hotel;

import com.example.team_pro_ex.Entity.mypetboard.common.BoardCommon;
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
public class HotelRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RoomNum;

    //외래키 설정
    @ManyToOne
    @JoinColumn(name = "HotelSeq", referencedColumnName = "HotelSeq")
    private Hotel hotel;

    @Column(nullable = false)
    private String RoomName;

    @Column(nullable = false)
    private String RoomInfo;

    @Column(nullable = false)
    private String RoomType;

    private Long R_HotelSeq;





}
