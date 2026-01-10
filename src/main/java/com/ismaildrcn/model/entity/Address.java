package com.ismaildrcn.model.entity;

import com.ismaildrcn.model.enums.AddressType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = true)
    private Restaurant restaurant;

    @PrePersist
    @PreUpdate
    private void validateOwner() {
        if ((this.user == null && this.restaurant == null) ||
                (this.user != null && this.restaurant != null)) {
            throw new IllegalStateException("Address must belong to either a User or a Restaurant, but not both.");
        }
    }

}
