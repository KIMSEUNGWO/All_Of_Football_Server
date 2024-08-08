package com.flutter.alloffootball.domain.user;

import com.flutter.alloffootball.domain.BaseEntityTime;
import com.flutter.alloffootball.domain.Cash;
import com.flutter.alloffootball.domain.coupon.UserCoupon;
import com.flutter.alloffootball.domain.orders.Order;
import com.flutter.alloffootball.enums.CashType;
import com.flutter.alloffootball.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
@Builder
public class User extends BaseEntityTime {

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String email;

    @Getter
    @Embedded
    private Social social;

    @Getter
    @Embedded
    private UserInfo userInfo;

    private String nickname;

    @Getter
    private int cash;

    @Enumerated(EnumType.STRING) @Getter
    private Role role;

    @Getter
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILE_ID")
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Order> orderList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Cash> receiptList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<UserCoupon> userCouponList = new ArrayList<>();

    public void receipt(CashType cashType, int receipt) {
        this.cash = Math.max(0, this.cash + cashType.accept(receipt));
    }
}
