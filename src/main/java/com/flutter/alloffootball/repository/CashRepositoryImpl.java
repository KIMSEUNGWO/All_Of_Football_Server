package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.common.domain.Cash;
import com.flutter.alloffootball.common.domain.orders.Order;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.common.enums.CashType;
import com.flutter.alloffootball.common.jparepository.JpaCashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CashRepositoryImpl implements CashRepository {

    private final JpaCashRepository jpaCashRepository;

    @Override
    public void use(User user, Order order) {

        int price = order.getPrice();
        user.receipt(CashType.USE, price);
        Cash saveCash = Cash.builder()
            .description("경기 참여")
            .user(user)
            .cashType(CashType.USE)
            .cashUse(-1 * price)
            .cashNow(user.getCash())
            .build();

        jpaCashRepository.save(saveCash);
    }

    @Override
    public List<Cash> findAllByReceipt(Long userId) {
        return jpaCashRepository.findAllByUser_IdOrderByCreateDateDesc(userId);
    }

}
