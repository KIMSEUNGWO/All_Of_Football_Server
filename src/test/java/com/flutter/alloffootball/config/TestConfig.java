package com.flutter.alloffootball.config;

import com.flutter.alloffootball.jparepository.*;
import com.flutter.alloffootball.mock.MockMatch;
import com.flutter.alloffootball.querydsl.QueryDslMatchRepository;
import com.flutter.alloffootball.querydsl.QueryDslMatchRepositoryImpl;
import com.flutter.alloffootball.repository.*;
import com.flutter.alloffootball.service.FieldService;
import com.flutter.alloffootball.service.FieldServiceImpl;
import com.flutter.alloffootball.service.OrderService;
import com.flutter.alloffootball.service.OrderServiceImpl;
import com.flutter.alloffootball.wrapper.FieldWrapper;
import com.flutter.alloffootball.wrapper.OrderWrapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Autowired
    private JpaFieldRepository jpaFieldRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Autowired
    private JpaMatchRepository jpaMatchRepository;
    @Autowired
    private JpaOrderRepository jpaOrderRepository;
    @Autowired
    private JpaCashRepository jpaCashRepository;
    @Autowired
    private JpaUserCouponRepository jpaUserCouponRepository;
    @Autowired
    private EntityManager em;

    @Bean
    JPAQueryFactory query() {
        return new JPAQueryFactory(em);
    }
    @Bean
    OrderService orderService() {
        return new OrderServiceImpl(userRepository(), matchRepository(), orderRepository(), cashRepository(), userCouponRepository(), orderWrapper());
    }

    @Bean
    OrderWrapper orderWrapper() {
        return new OrderWrapper();
    }
    @Bean
    FieldRepository fieldRepository() {
        return new FieldRepositoryImpl(jpaFieldRepository);
    }
    @Bean
    FieldService fieldService() {
        return new FieldServiceImpl(fieldRepository(), fieldWrapper());
    }

    @Bean
    FieldWrapper fieldWrapper() {
        return new FieldWrapper();
    }

    @Bean
    UserRepository userRepository() {
        return new UserRepositoryImpl(jpaUserRepository);
    }

    @Bean
    MatchRepository matchRepository() {
        return new MatchRepositoryImpl(jpaMatchRepository, queryDslMatchRepository());
    }

    @Bean
    QueryDslMatchRepository queryDslMatchRepository() {
        return new QueryDslMatchRepositoryImpl(query());
    }

    @Bean
    OrderRepository orderRepository() {
        return new OrderRepositoryImpl(matchRepository(), jpaOrderRepository);
    }

    @Bean
    CashRepository cashRepository() {
        return new CashRepositoryImpl(jpaCashRepository);
    }

    @Bean
    UserCouponRepository userCouponRepository() {
        return new UserCouponRepositoryImpl(jpaUserCouponRepository);
    }
}
