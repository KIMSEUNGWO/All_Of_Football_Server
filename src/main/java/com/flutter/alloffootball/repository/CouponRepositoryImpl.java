package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.jparepository.JpaCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final JpaCouponRepository jpaCouponRepository;
}
