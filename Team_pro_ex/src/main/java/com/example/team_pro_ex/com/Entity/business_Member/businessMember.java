package com.example.team_pro_ex.com.Entity.business_Member;

import com.example.team_pro_ex.com.Entity.Base.member_BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class businessMember extends member_BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long business_Number_Seq;

        @Column(name = "business_member_id", length = 20, nullable = false, unique = true)
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{8,16}", message = "아이디는 8~16자 영문 소문자, 숫자를 사용하세요.")
        private String id;  // 아이디

        @Column(name = "business_member_password",length = 18)
        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password; // 비밀번호

        @Column(name = "business_member_number", length = 20)
        private String businessNumber; //사업자 번호

        @Pattern(regexp = "(?=.*[0-9]).{11}", message = "핸드폰 번호는 예)010xxxxxxxx")
        @Column(name = "business_member_phone_number")
        private String phoneNumber; // 핸드폰 번호

        @Column(name = "business_member_join_M", length = 1, nullable = false)
        private String joinM = "Y"; //--가입상태

        @Column(name = "business_member_Role")
        private String Role = "ROLE_ADMIN";

//        @Pattern(regexp = "(?=.*[0-9]).{10}", message = "핸드폰 번호는 예)041xxxxxxx")
//        @Column(name = "business_member_store_number")
//        private String store_number; // 가게번호 번호
//
//        @Column(name = "business_store_name", length = 30)
//        private String store_Name; // 가게이름
//
//        @Column(name = "business_member_address", length = 50)
//        private String address; // 주소



}
