package com.example.teampro.Entity.baseEntity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@MappedSuperclass
public class baseTimeEntity {
    @CreatedDate
    private Date join_D; //--가입날짜

    @LastModifiedDate
    private Date Member_info_Update; //회원정보 수정날짜

    //08.24 생각해야할 부분 : 회원 탈퇴를 어떻게 할 지 생각을 해보자
    //LastModifiedDate, CreatedDate를 안 넣은 이유는 이 두가지 어노테이션을 넣으면
    //자동으로 테이블에 생성이 되기 때문에 탈퇴날짜는 어노테이션을 적지 않았다.

    private Date join_O; //--탈퇴날짜


}
