package com.ismaildrcn.model.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ismaildrcn.model.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements UserDetails {

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    @Column(name = "full_name")
    private String fullName;

    private Gender gender;

    @Column(name = "birth_of_date")
    private Date birthOfDate;

    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "phone_number_verified")
    private boolean phoneNumberVerified = false;

    // UserDetails implementation
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email; // Email username olarak kullanılacak
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return emailVerified; // Email doğrulanmışsa hesap aktif
    }
}
