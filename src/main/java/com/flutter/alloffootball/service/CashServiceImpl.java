package com.flutter.alloffootball.service;

import com.flutter.alloffootball.repository.CashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashServiceImpl implements CashService {

    private final CashRepository cashRepository;
}
