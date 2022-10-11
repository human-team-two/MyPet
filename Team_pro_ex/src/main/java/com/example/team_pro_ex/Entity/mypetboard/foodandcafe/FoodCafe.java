package com.example.team_pro_ex.Entity.mypetboard.foodandcafe;

import com.example.team_pro_ex.Entity.mypetboard.common.BoardCommon;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class FoodCafe extends BoardCommon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq" )
    private Long seq;

    private String detailCategory;

}
