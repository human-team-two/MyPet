package com.example.team_pro_ex.Entity.mypetboard.hotel;

import com.example.team_pro_ex.Entity.member.OwnerMember;
import com.example.team_pro_ex.Entity.mypetboard.common.BoardCommon;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Hotel extends BoardCommon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotelSeq" )
    private Long hotelSeq;

    @Column
    private Long H_OwnerMemberSeq;

    @Column
    private Long CategorySeq;

    //조인 외래키 설정 1:N  양방향 매핑 참조삭제제(호텔:룸)
    @OneToMany(mappedBy = "hotel" )
    private List<HotelRoom> HRoomList;

    //조인 사업자회원:호텔
    @ManyToOne
    @JoinColumn(name = "OwnerMemberSeq", referencedColumnName = "OwnerMemberSeq")
    private OwnerMember ownerMember;

}
