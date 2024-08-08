package com.flutter.alloffootball.domain.coupon;

import com.flutter.alloffootball.domain.BaseEntityTime;
import com.flutter.alloffootball.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_COUPON")
public class UserCoupon extends BaseEntityTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_COUPON_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPON_ID")
    private Coupon coupon;

    private LocalDateTime expireDate;
    @Setter
    private char couponUse;
}
