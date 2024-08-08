package com.flutter.alloffootball.jparepository;

import com.flutter.alloffootball.domain.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCashRepository extends JpaRepository<Cash, Long> {

    List<Cash> findAllByUser_Id(Long userId);
}
