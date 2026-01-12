package com.ismaildrcn.model.dto;

import java.util.Date;
import java.util.List;

import com.ismaildrcn.model.entity.Address;
import com.ismaildrcn.model.entity.Order;
import com.ismaildrcn.model.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserResponse {

    private Long id;

    private String email;

    private String phoneNumber;

    private String fullName;

    private Gender gender;

    private Date birthOfDate;

    private boolean emailVerified = false;

    private boolean phoneNumberVerified = false;

    private List<Address> address;

    private List<Order> orders;

}
