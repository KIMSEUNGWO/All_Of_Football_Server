package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.domain.Cash;
import com.flutter.alloffootball.domain.orders.Order;
import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.enums.CashType;
import com.flutter.alloffootball.jparepository.JpaCashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CashRepositoryImpl implements CashRepository {

    private final JpaCashRepository jpaCashRepository;

    @Override
    public void use(User user, Order order) {

        int price = order.getPrice();
        user.receipt(CashType.USE, price);
        Cash saveCash = Cash.builder()
            .user(user)
            .cashType(CashType.USE)
            .cashUse(-1 * price)
            .cashNow(user.getCash())
            .build();

        jpaCashRepository.save(saveCash);
    }

}
