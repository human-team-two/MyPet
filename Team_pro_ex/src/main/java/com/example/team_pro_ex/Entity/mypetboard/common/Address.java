package com.example.team_pro_ex.Entity.mypetboard.common;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
     private String postcode;
     private String detailAddress;
     private String Address;

     protected Address() {

    }
    public Address(String postcode, String detailAddress, String Address) {
         this.postcode = postcode;
         this.Address = Address;
         this.detailAddress = detailAddress;
    }
}

