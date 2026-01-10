package com.ismaildrcn.model.dto;

import java.util.Date;

import com.ismaildrcn.model.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUserIU {

    private String email;

    private String phoneNumber;

    private String password;

    private String fullName;

    private Gender gender;

    private Date birthOfDate;

}
