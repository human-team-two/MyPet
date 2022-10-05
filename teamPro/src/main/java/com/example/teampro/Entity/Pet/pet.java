package com.example.teampro.Entity.Pet;

import com.example.teampro.Entity.Member.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Pattern;


@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Table(name="pet")
public class pet {

    @Column(name = "member_pet_T", length = 20)
    private String petT; //--펫 종류

    @Pattern(regexp = "(?=.*[0-9])(?=.*\\W)(?=\\S+$).{10}", message = "애견,애묘의 출생일은 예)20220901")
    @Column(name = "member_pet_D")
    private String petD; //-- 펫 출생

    @Column(name = "member_pet_W", length = 10)
    private Integer petW; //--펫 몸무게

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Number_Seq")
    private Member member;




}
