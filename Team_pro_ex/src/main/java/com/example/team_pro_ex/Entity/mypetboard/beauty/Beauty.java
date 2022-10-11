package com.example.team_pro_ex.Entity.mypetboard.beauty;

import com.example.team_pro_ex.Entity.mypetboard.common.BoardCommon;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beauty extends BoardCommon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq" )
    private Long seq;



}
