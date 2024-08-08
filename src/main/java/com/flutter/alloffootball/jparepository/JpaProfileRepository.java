package com.flutter.alloffootball.jparepository;

import com.flutter.alloffootball.domain.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProfileRepository extends JpaRepository<Profile, Long> {
}
