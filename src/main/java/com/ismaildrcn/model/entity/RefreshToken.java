package com.ismaildrcn.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refresh_token", indexes = {
        @Index(name = "idx_refresh_token_user_id", columnList = "user_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RefreshToken extends BaseEntity {

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    @ManyToOne
    private User user;

}
