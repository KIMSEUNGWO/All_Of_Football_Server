package com.flutter.alloffootball.admin.service;

import com.flutter.alloffootball.admin.dto.ResponseSearchField;
import com.flutter.alloffootball.admin.repository.AdminRepository;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Page<ResponseSearchField> findAllBySearch(Region region, String word, Pageable pageable) {
        return adminRepository.findAllBySearch(region, word, pageable);
    }
}
