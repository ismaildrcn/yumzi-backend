package com.ismaildrcn.model.entity;

import com.ismaildrcn.model.enums.AddressType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity {

    private String title;

    @Column(name = "address_type")
    private AddressType addressType;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2", nullable = true)
    private String addressLine2;

    private String district;

    private String city;

    private String state;

    private String country;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String latitude;

    private String longitude;

    @Column(name = "is_default")
    private boolean isDefault = false;

    @Column(name = "is_active")
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
