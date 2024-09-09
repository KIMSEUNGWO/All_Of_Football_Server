package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.domain.Cash;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.common.enums.CashType;
import com.flutter.alloffootball.common.jparepository.JpaCashRepository;
import com.flutter.alloffootball.repository.PaymentRepository;
import com.flutter.alloffootball.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;


    @Override
    public void receipt(Long userId, String message, CashType cashType, int amount) {
        receipt(userRepository.findById(userId), message, cashType, amount);
    }

    @Override
    public void receipt(User user, String message, CashType cashType, int amount) {
        paymentRepository.receipt(user, message, cashType, amount);
    }


}