package com.flutter.alloffootball.jparepository;

import com.flutter.alloffootball.domain.match.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaMatchRepository extends JpaRepository<Match, Long> {

    List<Match> findAllByField_IdAndMatchDateAfter(long fieldId, Pageable pageable, LocalDateTime dateTime);
}
