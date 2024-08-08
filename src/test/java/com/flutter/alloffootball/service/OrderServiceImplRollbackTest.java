package com.flutter.alloffootball.service;

import com.flutter.alloffootball.config.MockConfig;
import com.flutter.alloffootball.config.TestConfig;
import com.flutter.alloffootball.domain.coupon.UserCoupon;
import com.flutter.alloffootball.domain.match.Match;
import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.domain.user.UserInfo;
import com.flutter.alloffootball.dto.order.RequestOrder;
import com.flutter.alloffootball.enums.MatchStatus;
import com.flutter.alloffootball.enums.Role;
import com.flutter.alloffootball.enums.SexType;
import com.flutter.alloffootball.exception.OrderError;
import com.flutter.alloffootball.exception.OrderException;
import com.flutter.alloffootball.jparepository.JpaUserCouponRepository;
import com.flutter.alloffootball.jparepository.JpaUserRepository;
import com.flutter.alloffootball.mock.MockCreator;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("test")
@DataJpaTest
@Import({TestConfig.class, MockConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderServiceImplRollbackTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MockCreator mockCreator;
    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Autowired
    private JpaUserCouponRepository jpaUserCouponRepository;

    @BeforeTransaction
    @Transactional
    void couponInit() {
        User user = mockCreator.mockUser(
            UserInfo.builder().birth(LocalDate.of(1996, 1, 10)).sex(SexType.MALE).build(),
            Role.USER,
            0);
        UserCoupon userCoupon = mockCreator.mockUserCoupon(
            mockCreator.mockCoupon(50, 100),
            user,
            'N',
            LocalDateTime.now().plusDays(100)
        );
        jpaUserRepository.flush();
        jpaUserCouponRepository.flush();
    }

    @AfterTransaction
    void rollbackTest() {
        Optional<UserCoupon> findUserCoupon = jpaUserCouponRepository.findById(1L);
        assertThat(findUserCoupon.isPresent()).isTrue();
        UserCoupon userCoupon = findUserCoupon.get();
        assertThat(userCoupon.getCouponUse()).isEqualTo('N');
    }

    @Test
    @DisplayName("쿠폰을 사용해도 캐시가 부족하면 쿠폰이 롤백되어야한다.")
    void 쿠폰_사용여부_롤백테스트() {

        int hourPrice = 10000;
        int matchTime = 2;
        // given
        Match match = mockCreator.mockMatch(
            mockCreator.mockField(hourPrice),
            LocalDateTime.now(),
            MatchStatus.OPEN,
            null,
            matchTime,
            3,
            6);

        RequestOrder requestOrder = new RequestOrder();
        requestOrder.setCouponId(1L);
        requestOrder.setMatchId(match.getId());

        // when
        assertThatThrownBy(() -> orderService.order(requestOrder, 1L, LocalDateTime.now()))
            .isInstanceOf(OrderException.class)
            .satisfies(e -> {
                assertThat(((OrderException) e).getError()).isEqualTo(OrderError.NOT_ENOUGH_CASH);
            });

    }

}
