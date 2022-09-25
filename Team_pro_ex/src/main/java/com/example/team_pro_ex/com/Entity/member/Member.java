package com.example.team_pro_ex.com.Entity.member;

import com.example.team_pro_ex.com.Entity.Base.member_BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

//@AllArgsConstructor : 모든 매개변수를 갖는 생성자
//@NoArgsConstructor(access = AccessLevel.PROTECTED) : 매개변수 없는 생성자
//@Builder

//@Entity JPA가 이 객체를 기준으로 table을 만들어야 한다고 선언
@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class Member extends member_BaseEntity {


    //builder패턴을 쓰면 중요하다고 생각 되는 것들은 builder를 사용하여 관리를 하고
    //그 외 요소들은 setter로 받는다.
    //builder를 사용하면 좋은 점? null처리에 대해서 쉽다.
    //나 이외의 다른 팀원이 실행할 경우 나는 어느 부분에서 null이 생겨날지 느낄 수 있지만
    //다른 팀원들은 잘 알 수가 없기 떄문이다.

    //Entity의 튜플의 순서에 따라 들어가는 값이 달라진다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_Number_Seq;


    @Column(name = "member_id", length = 20, nullable = false, unique = true)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{8,16}", message = "아이디는 8~16자 영문 소문자, 숫자를 사용하세요.")
    private String id;  // 아이디

    @Column(name = "member_password",length = 18)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자를 포함한 특수문자를 사용하세요.")
    private String password; // 비밀번호

    @Column(name = "member_name")
    private String name; // 이름

    @Pattern(regexp = "(?=.*[0-9])(?=.*\\W)(?=\\S+$).{10}", message = "출생년도는 예)20220901")
    @Column(name = "member_year")
    private String year; // 펫주인 생년월일

    @Pattern(regexp = "(?=.*[0-9]).{11}", message = "핸드폰 번호는 예)010xxxxxxxx")
    @Column(name = "member_phone_number")
    private String phoneNumber; // 핸드폰 번호

    @Column(name = "member_address", length = 50)
    private String address; // 주소

    @Column(name = "member_pet_T", length = 20)
    private String petT; //--펫 종류


    @Pattern(regexp = "(?=.*[0-9])(?=.*\\W)(?=\\S+$).{10}", message = "애견,애묘의 출생일은 예)20220901")
    @Column(name = "member_pet_D")
    private String petD; //-- 펫 출생

    @Column(name = "member_pet_W", length = 10)
    private Integer petW; //--펫 몸무게

    //권한에 대해 부여하기 위해서 변수를 만듬 : 관리자, 회원, 사업자를 나누기 위해서
    private String role;

    @Column(name = "member_join_M", length = 1, nullable = false)
    private String joinM = "Y"; //--가입상태




}

