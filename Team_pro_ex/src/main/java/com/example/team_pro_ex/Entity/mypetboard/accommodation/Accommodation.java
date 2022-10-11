package com.example.team_pro_ex.Entity.mypetboard.accommodation;

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
public class Accommodation extends BoardCommon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Acc_seq")
    private Long seq;


    private Long ownerMemberSeq;


    private Long categorySeq;


    private String detailCategory;

    //조인 외래키 설정 1:N  양방향 매핑 참조삭제(숙소:룸)
    @OneToMany(mappedBy = "accommodation" , cascade = CascadeType.ALL)
    private List<Room> RoomList;
//
//    public void add(Room room) {
//        room.setAccommodation();
//        getRoomList().add(room);
//    }
    //조인 사업자회원:숙소
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ownerMemberSeq")
    private OwnerMember ownerMember;


    public static Room createRoom(Accommodation accommodation){
        Room room = new Room();
        room.setAccommodation(accommodation);
        return room;
    }

}
